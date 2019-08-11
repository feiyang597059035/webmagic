package com.carwel.webmagic.service.impl;

import com.carwel.webmagic.config.resultcode.ErrorCode;
import com.carwel.webmagic.config.resultcode.Result;

import com.carwel.webmagic.config.resultcode.Results;
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
           return Results.success( result);
       }
        return  Results.failure(ErrorCode.SERVER_ERROR);
    }

    /**
     * 用户收藏 审核通过
     *
     * @param createUserInterestRequest
     * @return
     */
    @Override
    public Result<Boolean> aduitSuccessUserInterest(CreateUserInterestRequest createUserInterestRequest) {
        Integer response= userInterestManager.aduitSuccessUserInterest(createUserInterestRequest);
        if (response!=null&&response>=1){
            boolean  result=true;
            return  Results.success(result);
        }
        return  Results.failure(ErrorCode.SERVER_ERROR);
    }

    /**
     * 用户收藏 审核失败
     *
     * @param id
     * @return
     */
    @Override
    public Result<Boolean> aduitFailUserInterest(Long id) {
        Integer response= userInterestManager.aduitFali(id);
        if (response!=null&&response>=1){
             boolean  result=true;
            return  Results.success(result);
        }
        return  Results.failure(ErrorCode.SERVER_ERROR);
    }
}
