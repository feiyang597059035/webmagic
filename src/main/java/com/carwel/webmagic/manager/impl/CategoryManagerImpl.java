package com.carwel.webmagic.manager.impl;

import com.carwel.webmagic.dao.CategoryDao;
import com.carwel.webmagic.manager.CategoryManager;
import com.carwel.webmagic.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryManagerImpl implements CategoryManager {
    @Autowired
    private CategoryDao categoryDao;
    @Override
    public Category getCategory(Integer categoryType) {
        Category category=new Category();
        category.setCategoryType(categoryType);
        return categoryDao.getCategory(category);
    }
}
