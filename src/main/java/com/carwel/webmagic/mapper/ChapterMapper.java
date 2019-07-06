package com.carwel.webmagic.mapper;

import com.carwel.webmagic.model.Chapter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChapterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    Chapter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKey(Chapter record);
}