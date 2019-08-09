package com.carwel.webmagic.manager.userinterest;


import com.carwel.webmagic.request.CreateUserInterestRequest;

public interface UserInterestManager {
    /**
     * 创建
     * @param createUserInterestRequest
     * @return
     */
    public int createUserInterest(CreateUserInterestRequest createUserInterestRequest);


    /**
     * 审核失败
     * @param id
     * @return
     */
    public int aduitFali(Long id);
}
