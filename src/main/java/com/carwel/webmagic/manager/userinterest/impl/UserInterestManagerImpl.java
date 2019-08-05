package com.carwel.webmagic.manager.userinterest.impl;

import com.carwel.webmagic.dao.UserInterestDao;
import com.carwel.webmagic.fsm.context.MapContext;

import com.carwel.webmagic.manager.userinterest.UserInterestManager;
import com.carwel.webmagic.manager.userinterest.fsm.UserInterestFSMOrder;
import com.carwel.webmagic.manager.userinterest.fsm.UserInterestOrderFSM;
import com.carwel.webmagic.manager.userinterest.fsm.event.UserInterestAduitFailEvent;
import com.carwel.webmagic.manager.userinterest.fsm.event.UserInterestCreateOrderEvent;
import com.carwel.webmagic.model.UserInterest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserInterestManagerImpl implements UserInterestManager {
    @Autowired
    private UserInterestCreateOrderEvent userInterestCreateOrderEvent;
    @Autowired
    private UserInterestAduitFailEvent userInterestAduitFailEvent;
    @Autowired
    private UserInterestOrderFSM userInterestOrderFSM;
    @Autowired
    private UserInterestDao userInterestDao;
    /**
     * 创建
     *
     * @param userInterest
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int createUserInterest(UserInterest userInterest) {
        MapContext context = new MapContext();
        context.bind("request",userInterest);
        Integer ret =(Integer) userInterestOrderFSM.onEvent(null,context,userInterestCreateOrderEvent);
        return ret;
    }

    /**
     * 审核失败
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int aduitFali(Long id) {
        UserInterest userInterest = userInterestDao.getUserInterestById(id);
        UserInterestFSMOrder order = new UserInterestFSMOrder(userInterest);

        Integer resp = (Integer) userInterestOrderFSM.onEvent(order,null,userInterestAduitFailEvent);
        return resp;
    }
}
