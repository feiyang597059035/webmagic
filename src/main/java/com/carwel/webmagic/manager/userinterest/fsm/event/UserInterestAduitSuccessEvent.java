package com.carwel.webmagic.manager.userinterest.fsm.event;


import com.carwel.webmagic.fsm.context.Context;
import com.carwel.webmagic.fsm.event.AbstractUpdateEvent;
import com.carwel.webmagic.manager.userinterest.fsm.UserInterestFSMOrder;

import org.springframework.stereotype.Component;

@Component
public class UserInterestAduitSuccessEvent extends AbstractUpdateEvent<UserInterestFSMOrder> {

    @Override
    public Object doUpdate(UserInterestFSMOrder order, Context context) {

        return 1;

    }
}
