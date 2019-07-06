package com.carwel.webmagic.dao;

import com.carwel.webmagic.model.Category;

public interface CategoryDao {
    int insert(Category record);

    Category getCategory(Category category);
}
