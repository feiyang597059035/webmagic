package com.carwel.webmagic.mapper;

import com.carwel.webmagic.model.Content;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);


    Content selectByContentName(String contentName);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);
}