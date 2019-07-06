package com.carwel.webmagic.mapper;

import com.carwel.webmagic.model.User;
import org.apache.ibatis.annotations.Mapper;

import javax.jws.soap.SOAPBinding;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserByUserId(String userId);
}