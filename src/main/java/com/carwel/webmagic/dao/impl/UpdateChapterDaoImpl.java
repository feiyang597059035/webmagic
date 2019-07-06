package com.carwel.webmagic.dao.impl;

import com.carwel.webmagic.dao.UpdateChapterDao;
import com.carwel.webmagic.mapper.UpdateChapterMapper;
import com.carwel.webmagic.model.UpdateChapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UpdateChapterDaoImpl implements UpdateChapterDao {
    @Autowired
    private UpdateChapterMapper updateChapterMapper;
    @Override
    public int insert(UpdateChapter record) {
        return updateChapterMapper.insert(record);
    }
}
