package com.carwel.webmagic.dao;

import com.carwel.webmagic.model.Content;

public interface ContentDao {
    int insert(Content record);

    Content getContentById(Long id);
}
