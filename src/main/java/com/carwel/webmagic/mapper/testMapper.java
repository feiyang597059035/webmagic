package com.carwel.webmagic.mapper;

import com.carwel.webmagic.model.test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface testMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(test record);

    int insertSelective(test record);

    test selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(test record);

    int updateByPrimaryKey(test record);
}