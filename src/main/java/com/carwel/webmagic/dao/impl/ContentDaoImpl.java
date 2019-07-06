package com.carwel.webmagic.dao.impl;

import com.carwel.webmagic.dao.ContentDao;
import com.carwel.webmagic.mapper.ContentMapper;
import com.carwel.webmagic.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContentDaoImpl implements ContentDao {
    @Autowired
    private ContentMapper contentMapper;
    @Override
    public int insert(Content record) {
        return contentMapper.insert(record);
    }
}
