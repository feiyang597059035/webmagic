package com.carwel.webmagic.manager;

import com.carwel.webmagic.dto.ChapterESInfoDTO;
import com.carwel.webmagic.dto.ChapterInfoDTO;

import org.apache.rocketmq.client.producer.SendResult;

/**
 * 章节信息
 */
public interface ChapterManager {
    /**
     * 保存剑来章节信息
     * @param chapterInfoDTO
     * @return
     */
    SendResult insertJianlaiChapter(ChapterInfoDTO chapterInfoDTO);

    /**
     * 获取es 索引信息
     * @param id
     * @return
     */
    ChapterESInfoDTO getChapterESInfoByChapterId(Long id);
}
