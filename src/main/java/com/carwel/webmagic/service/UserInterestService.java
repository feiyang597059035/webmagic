package com.carwel.webmagic.service;

import com.carwel.webmagic.config.resultcode.Result;
import com.carwel.webmagic.request.CreateUserInterestRequest;
import org.springframework.stereotype.Service;


public interface UserInterestService {
    /**
     * 添加用户
     * @param createUserInterestRequest
     * @return
     */
    public Result<Boolean> addUserInterest(CreateUserInterestRequest createUserInterestRequest);
}
