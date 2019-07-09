package com.carwel.webmagic.manager.impl;

import com.carwel.webmagic.dao.ChapterDao;
import com.carwel.webmagic.dto.ChapterInfoDTO;
import com.carwel.webmagic.manager.ChapterManager;
import com.carwel.webmagic.model.Chapter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class ChapterManagerImpl implements ChapterManager {
    @Autowired
    private ChapterDao chapterDao;
    /**
     * 保存剑来章节信息
     *
     * @param chapterInfoDTO
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int insertJianlaiChapter(ChapterInfoDTO chapterInfoDTO) {

        //判断是否已抓取

        List<Chapter> list=chapterDao.getChapterByChapterNum(chapterInfoDTO.getChapterNum(),
                chapterInfoDTO.getContentId());
        if(CollectionUtils.isNotEmpty(list)){
            return 1;
        }
        Chapter chapter=new Chapter();
        chapter.setChapterContext(chapterInfoDTO.getChapterContext());
        chapter.setChapterName(chapterInfoDTO.getChapterName());
        chapter.setChapterNum(chapterInfoDTO.getChapterNum());
        chapter.setContentId(chapterInfoDTO.getContentId());
        chapter.setGmtCreated(new Date());
        chapter.setGmtModifild(new Date());
        return  chapterDao.insert(chapter);
    }
}
