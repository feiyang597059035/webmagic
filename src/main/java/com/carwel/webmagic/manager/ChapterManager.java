package com.carwel.webmagic.manager;

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
}
