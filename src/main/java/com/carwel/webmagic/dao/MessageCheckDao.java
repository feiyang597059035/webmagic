package com.carwel.webmagic.dao;

import com.carwel.webmagic.model.MessageCheck;

import java.util.List;

public interface MessageCheckDao {
    List<MessageCheck> getListMessageCheck(MessageCheck messageCheck);

    int insert(MessageCheck messageCheck);
}
