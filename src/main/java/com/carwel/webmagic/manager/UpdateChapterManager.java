package com.carwel.webmagic.manager;

import com.carwel.webmagic.dto.ChapterInfoDTO;

public interface UpdateChapterManager {
   /**
    * 处理剑来的更新章节信息
    * @param chapterInfoDTO
    * @return
    */
   int  updateJianlaiUpdateChapter(ChapterInfoDTO chapterInfoDTO);
}
