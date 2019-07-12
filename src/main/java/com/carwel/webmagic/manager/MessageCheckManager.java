package com.carwel.webmagic.manager;

import com.carwel.webmagic.model.MessageCheck;

import java.util.List;

public interface MessageCheckManager {
    List<MessageCheck> getListMessageCheck(MessageCheck messageCheck);

    int insert(MessageCheck messageCheck);
}
