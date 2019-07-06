package com.carwel.webmagic.mapper;

import com.carwel.webmagic.model.UserInterest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInterestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInterest record);

    int insertSelective(UserInterest record);

    UserInterest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInterest record);

    int updateByPrimaryKey(UserInterest record);

    List<UserInterest> getListUserInterestByUserId(String userId);
}