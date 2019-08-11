package com.carwel.webmagic.service;

import com.carwel.webmagic.config.resultcode.Result;

import com.carwel.webmagic.dto.ChapterESInfoDTO;

public interface ChapterService {
    /**
     * 根据内容id 和章节获取信息
     * @param contentId
     * @param chapterNum
     * @return
     */
    Result<ChapterESInfoDTO> getChapterESInfoDTOByContentId(Integer contentId, Integer chapterNum);
}
