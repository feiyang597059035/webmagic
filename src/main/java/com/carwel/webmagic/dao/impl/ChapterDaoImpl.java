package com.carwel.webmagic.dao.impl;

import com.carwel.webmagic.dao.ChapterDao;
import com.carwel.webmagic.mapper.ChapterMapper;
import com.carwel.webmagic.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChapterDaoImpl implements ChapterDao {
    @Autowired
    private ChapterMapper chapterMapper;
    @Override
    public Long insert(Chapter record) {
        int i= chapterMapper.insert(record);
        if (i>0){
            return  record.getId();
        }
        return  0L;
    }

    /**
     * 根据章节和内容id 获取
     *
     * @param chapterNum
     * @param contentId
     * @return
     */
    @Override
    public List<Chapter> getChapterByChapterNum(Integer chapterNum, Integer contentId) {
        return chapterMapper.getChapterByChapterNum(chapterNum,contentId);
    }

    @Override
    public Chapter getChapterInfoById(Long id) {
        return chapterMapper.selectByPrimaryKey(id);
    }
}
