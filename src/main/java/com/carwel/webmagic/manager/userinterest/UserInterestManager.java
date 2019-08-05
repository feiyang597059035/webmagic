package com.carwel.webmagic.manager.userinterest;

import com.carwel.webmagic.model.UserInterest;

public interface UserInterestManager {
    /**
     * 创建
     * @param userInterest
     * @return
     */
    public int createUserInterest(UserInterest userInterest);


    /**
     * 审核失败
     * @param id
     * @return
     */
    public int aduitFali(Long id);
}
