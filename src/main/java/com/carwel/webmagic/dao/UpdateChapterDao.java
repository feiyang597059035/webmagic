package com.carwel.webmagic.dao;

import com.carwel.webmagic.model.UpdateChapter;

public interface UpdateChapterDao {
    int insert(UpdateChapter record);

    UpdateChapter getUpdateChapterByContentId(Integer contentId);

    int  updateUpdateChapter(UpdateChapter updateChapter);
}
