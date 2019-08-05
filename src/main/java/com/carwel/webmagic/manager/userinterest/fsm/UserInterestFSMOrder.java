package com.carwel.webmagic.manager.userinterest.fsm;


import com.carwel.webmagic.enums.Status;
import com.carwel.webmagic.enums.UserInterestStatusEnum;
import com.carwel.webmagic.fsm.Order;

import com.carwel.webmagic.model.UserInterest;

import org.springframework.beans.BeanUtils;

public class UserInterestFSMOrder extends UserInterest implements Order {

    public UserInterestFSMOrder() {
    }

    public UserInterestFSMOrder(UserInterest userInterest) {
        BeanUtils.copyProperties(userInterest,this);
    }

    @Override
    public void setFSMStatus(Status status) {
        setStatus(status.getValue());
    }

    @Override
    public Status getFSMStatus() {
        return UserInterestStatusEnum.getByCode(getStatus().intValue());
    }
}
