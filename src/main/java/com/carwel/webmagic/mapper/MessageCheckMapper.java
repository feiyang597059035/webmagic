package com.carwel.webmagic.mapper;

import com.carwel.webmagic.model.MessageCheck;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageCheckMapper {
    int deleteByPrimaryKey(MessageCheck key);

    int insert(MessageCheck record);

    int insertSelective(MessageCheck record);


    List<MessageCheck> getListMessageCheck(MessageCheck messageCheck);
}