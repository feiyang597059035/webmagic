package com.carwel.webmagic.dao;

import com.carwel.webmagic.model.UserInterest;

import java.util.List;

public interface UserInterestDao {
    int insert(UserInterest record);

    List<UserInterest> getListUserInterestByUserId(String userId);
}
