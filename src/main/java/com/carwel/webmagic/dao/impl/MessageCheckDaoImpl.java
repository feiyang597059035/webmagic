package com.carwel.webmagic.dao.impl;

import com.carwel.webmagic.dao.MessageCheckDao;
import com.carwel.webmagic.mapper.MessageCheckMapper;
import com.carwel.webmagic.model.MessageCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MessageCheckDaoImpl implements MessageCheckDao {
    @Autowired
    private MessageCheckMapper messageCheckMapper;
    @Override
    public List<MessageCheck> getListMessageCheck(MessageCheck messageCheck) {
        return  messageCheckMapper.getListMessageCheck(messageCheck);
    }

    @Override
    public int insert(MessageCheck messageCheck) {
        return messageCheckMapper.insert(messageCheck);
    }
}
