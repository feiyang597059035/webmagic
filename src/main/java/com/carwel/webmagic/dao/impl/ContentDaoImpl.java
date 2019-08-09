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
    public Long insert(Content record) {
        int i= contentMapper.insert(record);
        if (i>0){
            return  record.getId();
        }
        return 0L;
    }

    @Override
    public Content getContentById(Long id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据内容名称查询
     *
     * @param contentName
     * @return
     */
    @Override
    public Content getContentByContentName(String contentName) {
        return contentMapper.selectByContentName(contentName);
    }
}
