package com.carwel.webmagic.manager;

import com.carwel.webmagic.dto.ChapterInfoDTO;
import org.apache.rocketmq.client.producer.SendResult;

public interface CommonManager {
    /**
     * 保存剑来章节信息
     * @param chapterInfoDTO
     * @return
     */
    int insertJianlaiChapter(ChapterInfoDTO chapterInfoDTO);
}
