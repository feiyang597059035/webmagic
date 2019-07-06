package com.carwel.webmagic.dao.impl;

import com.carwel.webmagic.dao.CategoryDao;
import com.carwel.webmagic.mapper.CategoryMapper;
import com.carwel.webmagic.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public int insert(Category record) {
        return categoryMapper.insert(record);
    }

    @Override
    public Category getCategory(Category category) {
        return categoryMapper.getCategory(category);
    }
}
