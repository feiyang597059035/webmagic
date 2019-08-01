package com.carwel.webmagic.manager.impl;

import com.carwel.webmagic.config.ConfigConstant;
import com.carwel.webmagic.dao.ChapterDao;
import com.carwel.webmagic.dto.ChapterInfoDTO;
import com.carwel.webmagic.manager.ChapterManager;
import com.carwel.webmagic.manager.SendMQManager;
import com.carwel.webmagic.model.Chapter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class ChapterManagerImpl implements ChapterManager {
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private SendMQManager sendMQManager;
    /**
     * 保存剑来章节信息
     *
     * @param chapterInfoDTO
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public SendResult insertJianlaiChapter(ChapterInfoDTO chapterInfoDTO) {

        SendResult sendResult=null;
        //判断是否已抓取
        List<Chapter> list=chapterDao.getChapterByChapterNum(chapterInfoDTO.getChapterNum(),
                chapterInfoDTO.getContentId());
        if(CollectionUtils.isNotEmpty(list)){
            return null;
        }
        Chapter chapter=new Chapter();
        chapter.setChapterContext(chapterInfoDTO.getChapterContext());
        chapter.setChapterName(chapterInfoDTO.getChapterName());
        chapter.setChapterNum(chapterInfoDTO.getChapterNum());
        chapter.setContentId(chapterInfoDTO.getContentId());
        chapter.setGmtCreated(new Date());
        chapter.setGmtModifild(new Date());
        Long id=  chapterDao.insert(chapter);
        if (id>0){
            //发送mq
             sendResult=sendMQManager.sendMQTransactionMessage(ConfigConstant.getEsTopic(),"es",
                    id.toString());
        }
        return sendResult;
    }
}
