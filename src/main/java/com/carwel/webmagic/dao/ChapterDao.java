package com.carwel.webmagic.dao;

import com.carwel.webmagic.model.Chapter;

import java.util.List;

public interface ChapterDao {
    Long insert(Chapter record);

    /**
     * 根据章节和内容id 获取
     * @param chapterNum
     * @param contentId
     * @return
     */
    List<Chapter> getChapterByChapterNum(Integer chapterNum, Integer contentId );
}
