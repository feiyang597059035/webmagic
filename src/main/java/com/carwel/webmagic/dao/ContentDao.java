package com.carwel.webmagic.dao;

import com.carwel.webmagic.model.Content;

public interface ContentDao {
    Long insert(Content record);

    Content getContentById(Long id);

    /**
     * 根据内容名称查询
     * @param contentName
     * @return
     */
    Content getContentByContentName(String contentName);
}
