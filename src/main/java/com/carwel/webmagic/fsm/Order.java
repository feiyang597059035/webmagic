package com.carwel.webmagic.fsm;


import com.carwel.webmagic.enums.Status;

public interface Order {

    Status getFSMStatus();

    void setFSMStatus(Status status);

}
