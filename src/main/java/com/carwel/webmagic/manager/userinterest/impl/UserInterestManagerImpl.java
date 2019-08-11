package com.carwel.webmagic.manager.userinterest.impl;

import com.carwel.webmagic.config.resultcode.BusinessException;
import com.carwel.webmagic.config.resultcode.ErrorCode;
import com.carwel.webmagic.dao.UserInterestDao;
import com.carwel.webmagic.fsm.context.MapContext;

import com.carwel.webmagic.manager.ContentManager;
import com.carwel.webmagic.manager.userinterest.UserInterestManager;
import com.carwel.webmagic.manager.userinterest.fsm.UserInterestFSMOrder;
import com.carwel.webmagic.manager.userinterest.fsm.UserInterestOrderFSM;
import com.carwel.webmagic.manager.userinterest.fsm.event.UserInterestAduitFailEvent;
import com.carwel.webmagic.manager.userinterest.fsm.event.UserInterestAduitSuccessEvent;
import com.carwel.webmagic.manager.userinterest.fsm.event.UserInterestCreateOrderEvent;
import com.carwel.webmagic.model.Content;
import com.carwel.webmagic.model.UserInterest;
import com.carwel.webmagic.request.CreateUserInterestRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
public class UserInterestManagerImpl implements UserInterestManager {
    @Autowired
    private UserInterestCreateOrderEvent userInterestCreateOrderEvent;
    @Autowired
    private UserInterestAduitFailEvent userInterestAduitFailEvent;
    @Autowired
    private UserInterestOrderFSM userInterestOrderFSM;
    @Autowired
    private UserInterestDao userInterestDao;
    @Autowired
    private ContentManager contentManager;
    @Autowired
    private UserInterestAduitSuccessEvent userInterestAduitSuccessEvent;
    /**
     * 创建
     *
     * @param createUserInterestRequest
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int createUserInterest(CreateUserInterestRequest createUserInterestRequest) {

        MapContext context = new MapContext();
        context.bind("request",createUserInterestRequest);
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
        if (resp==null||resp.intValue()<=0){
            throw  new BusinessException(ErrorCode.SERVER_ERROR);
        }
        return resp;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int aduitSuccessUserInterest(CreateUserInterestRequest createUserInterestRequest) {
        UserInterest userInterest = userInterestDao.getUserInterestById(createUserInterestRequest.getUserInterestId());
        UserInterestFSMOrder order = new UserInterestFSMOrder(userInterest);
        MapContext context = new MapContext();
        context.bind("request",createUserInterestRequest);
        Integer resp = (Integer) userInterestOrderFSM.onEvent(order,context,userInterestAduitSuccessEvent);
        if (resp==null||resp.intValue()<=0){
            throw  new BusinessException(ErrorCode.SERVER_ERROR);
        }
        return resp;
    }
}
