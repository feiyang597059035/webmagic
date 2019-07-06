package com.carwel.webmagic.mapper;

import com.carwel.webmagic.model.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    Category getCategory(Category category);
}