package com.carwel.webmagic.manager.userinterest.fsm;

import com.carwel.webmagic.dao.UserInterestDao;
import com.carwel.webmagic.enums.UserInterestStatusEnum;
import com.carwel.webmagic.fsm.AbstractOrderFSM;
import com.carwel.webmagic.fsm.Order;
import com.carwel.webmagic.enums.Status;
import com.carwel.webmagic.fsm.context.Context;
import com.carwel.webmagic.fsm.event.Event;
import com.carwel.webmagic.manager.userinterest.fsm.event.UserInterestAduitFailEvent;
import com.carwel.webmagic.manager.userinterest.fsm.event.UserInterestAduitSuccessEvent;
import com.carwel.webmagic.manager.userinterest.fsm.event.UserInterestCreateOrderEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class UserInterestOrderFSM extends AbstractOrderFSM {

    @Autowired
    UserInterestDao userInterestDao;

    @Override
    public int updateStatus(Order order, Context context, Status status) {
        //暂不考虑ABA的问题，否则就要使用gmtModified
        UserInterestFSMOrder itemOrder = (UserInterestFSMOrder)order;
        return userInterestDao.updateStatus(itemOrder.getId(),itemOrder.getStatus(),status.getValue());
    }

    @Override
    public Map<Status, Map<Class, Status>> findMap(Order order, Context context) {
        //状态机工厂,初期硬编码
        Map<Status, Map<Class, Status>> statusMapMap = new HashMap<>();

        //新建订单
        Map<Class, Status> createMap = new HashMap<>();
        createMap.put(UserInterestCreateOrderEvent.class, UserInterestStatusEnum.CREATED);
        statusMapMap.put(null,createMap);

        //审核
        Map<Class, Status> payMap = new HashMap<>();
        payMap.put(UserInterestAduitSuccessEvent.class,UserInterestStatusEnum.SUCCESS);
        payMap.put(UserInterestAduitFailEvent.class,UserInterestStatusEnum.FAIL);

        statusMapMap.put(UserInterestStatusEnum.CREATED,payMap);

        return statusMapMap;
    }

    @Override
    public void addEventLog(Order order, Context context, Event event) {
        log.info("order:{},context:{},event:{}",order,context,event);
        UserInterestFSMOrder itemOrder = null;
        if(event instanceof UserInterestCreateOrderEvent){
            itemOrder = (UserInterestFSMOrder) context.lookup("order");
        }
        else{
            itemOrder = (UserInterestFSMOrder)order;
        }
        //可以插入数据库


    }

}
