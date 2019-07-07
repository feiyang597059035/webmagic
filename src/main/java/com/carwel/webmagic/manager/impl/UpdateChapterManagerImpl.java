package com.carwel.webmagic.manager.impl;

import com.carwel.webmagic.dao.UpdateChapterDao;
import com.carwel.webmagic.dto.ChapterInfoDTO;
import com.carwel.webmagic.manager.UpdateChapterManager;
import com.carwel.webmagic.model.UpdateChapter;
import com.carwel.webmagic.util.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;

/**
 * 处理最新更新章节
 */
@Component
public class UpdateChapterManagerImpl implements UpdateChapterManager {
    @Autowired
    private UpdateChapterDao updateChapterDao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public int updateJianlaiUpdateChapter(ChapterInfoDTO chapterInfoDTO) {
        Date updateTime=new Date();
        if (StringUtils.isNotBlank(chapterInfoDTO.getUpdateTime())){
             updateTime= DateTimeUtils.formatDate(chapterInfoDTO.getUpdateTime(),
                    DateTimeUtils.DATE_TIME_STYLE);

        }
        UpdateChapter updateChapter=updateChapterDao.getUpdateChapterByContentId(chapterInfoDTO.getContentId());
        //更新最新章节信息
        if (updateChapter!=null&&updateChapter.getId()>0){
            updateChapter.setGmtModifiled(new Date());
            updateChapter.setLastestUpdateTime(updateTime);
            updateChapter.setChapterName(chapterInfoDTO.getChapterName());
            updateChapter.setChapterNum(chapterInfoDTO.getChapterNum());
         return    updateChapterDao.updateUpdateChapter(updateChapter);
        }else {
            UpdateChapter newUpdateChapter=new UpdateChapter();
            newUpdateChapter.setChapterNum(chapterInfoDTO.getChapterNum());
            newUpdateChapter.setChapterName(chapterInfoDTO.getChapterName());
            newUpdateChapter.setLastestUpdateTime(updateTime);
            newUpdateChapter.setContentId(chapterInfoDTO.getContentId());
            newUpdateChapter.setGmtCreated(new Date());
            newUpdateChapter.setGmtModifiled(new Date());
            return updateChapterDao.insert(newUpdateChapter);
        }

    }
}
