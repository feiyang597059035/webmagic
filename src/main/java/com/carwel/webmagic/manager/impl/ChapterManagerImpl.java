package com.carwel.webmagic.manager.impl;

import com.carwel.webmagic.config.ConfigConstant;
import com.carwel.webmagic.dao.ChapterDao;
import com.carwel.webmagic.dto.ChapterESInfoDTO;
import com.carwel.webmagic.dto.ChapterInfoDTO;
import com.carwel.webmagic.manager.CategoryManager;
import com.carwel.webmagic.manager.ChapterManager;
import com.carwel.webmagic.manager.ContentManager;
import com.carwel.webmagic.manager.SendMQManager;
import com.carwel.webmagic.model.Category;
import com.carwel.webmagic.model.Chapter;
import com.carwel.webmagic.model.Content;
import com.carwel.webmagic.util.DataConvertUtils;
import com.carwel.webmagic.util.DateTimeUtils;
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
    @Autowired
    private ContentManager contentManager;
    @Autowired
    private CategoryManager categoryManager;
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

    /**
     * 获取es 索引信息
     *
     * @param id
     * @return
     */
    @Override
    public ChapterESInfoDTO getChapterESInfoByChapterId(Long id) {
        Chapter chapter=chapterDao.getChapterInfoById(id);
        if (chapter!=null){
            ChapterESInfoDTO chapterESInfoDTO=new ChapterESInfoDTO();
            DataConvertUtils.convertDO2DTO(chapter,chapterESInfoDTO);
            chapterESInfoDTO.setGmtModifild(DateTimeUtils.formatString(chapter.getGmtModifild(),DateTimeUtils.DATE_TIME_STYLE));
            //获取content 名称
            Content content=contentManager.getContentById(Long.valueOf(chapter.getContentId()));
            chapterESInfoDTO.setContentName(content.getContentName());
            chapterESInfoDTO.setCategoryType(content.getCategoryType());
            //获取类目名称
            Category category=categoryManager.getCategory(content.getCategoryType());
            chapterESInfoDTO.setCategoryName(category.getCategoryName());
            return  chapterESInfoDTO;
        }

        return null;
    }
}
