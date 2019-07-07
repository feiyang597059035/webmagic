package com.carwel.webmagic.manager;

import com.carwel.webmagic.dto.ChapterInfoDTO;

/**
 * 章节信息
 */
public interface ChapterManager {
    /**
     * 保存剑来章节信息
     * @param chapterInfoDTO
     * @return
     */
    int insertJianlaiChapter(ChapterInfoDTO chapterInfoDTO);
}
