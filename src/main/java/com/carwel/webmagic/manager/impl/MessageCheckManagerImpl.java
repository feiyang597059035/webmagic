package com.carwel.webmagic.manager.impl;

import com.carwel.webmagic.dao.MessageCheckDao;
import com.carwel.webmagic.manager.MessageCheckManager;
import com.carwel.webmagic.model.MessageCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MessageCheckManagerImpl implements MessageCheckManager {
    @Autowired
    private MessageCheckDao messageCheckDao;

    @Override
    public List<MessageCheck> getListMessageCheck(MessageCheck messageCheck) {
        return messageCheckDao.getListMessageCheck(messageCheck);
    }

    @Override
    public int insert(MessageCheck messageCheck) {
        return messageCheckDao.insert(messageCheck);
    }
}
