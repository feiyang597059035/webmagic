package com.carwel.webmagic.service.impl;

import com.carwel.webmagic.config.resultcode.CodeMsg;
import com.carwel.webmagic.config.resultcode.Result;
import com.carwel.webmagic.manager.userinterest.UserInterestManager;
import com.carwel.webmagic.request.CreateUserInterestRequest;
import com.carwel.webmagic.service.UserInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInterestServiceImpl implements UserInterestService {
    @Autowired
    private UserInterestManager userInterestManager;
    /**
     * 添加用户
     *
     * @param createUserInterestRequest
     * @return
     */
    @Override
    public Result<Boolean> addUserInterest(CreateUserInterestRequest createUserInterestRequest) {
        boolean result=false;
       Integer response= userInterestManager.createUserInterest(createUserInterestRequest);
       if (response!=null&&response>=1){
           result=true;
           return  Result.write(CodeMsg.SUCCESS,result);
       }
        return  Result.error(CodeMsg.SERVER_ERROR);
    }
}
