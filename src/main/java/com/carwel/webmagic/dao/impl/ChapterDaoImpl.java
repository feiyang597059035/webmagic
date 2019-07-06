package com.carwel.webmagic.dao.impl;

import com.carwel.webmagic.dao.ChapterDao;
import com.carwel.webmagic.mapper.ChapterMapper;
import com.carwel.webmagic.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChapterDaoImpl implements ChapterDao {
    @Autowired
    private ChapterMapper chapterMapper;
    @Override
    public int insert(Chapter record) {
        return chapterMapper.insert(record);
    }
}
