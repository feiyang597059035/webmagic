package com.carwel.webmagic.service;

import com.carwel.webmagic.config.resultcode.Result;
import com.carwel.webmagic.request.CreateUserInterestRequest;



public interface UserInterestService {
    /**
     * 添加用户 收藏
     * @param createUserInterestRequest
     * @return
     */
    public Result<Boolean> addUserInterest(CreateUserInterestRequest createUserInterestRequest);

    /**
     * 用户收藏 审核通过
     * @param createUserInterestRequest
     * @return
     */
    public Result<Boolean> aduitSuccessUserInterest(CreateUserInterestRequest createUserInterestRequest);

    /**
     * 用户收藏 审核失败
     * @param id
     * @return
     */
    public Result<Boolean> aduitFailUserInterest(Long id);


}
