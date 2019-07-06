package com.carwel.webmagic.dao;

import com.carwel.webmagic.model.User;

public interface UserDao {
    int insert(User record);

    User getUserByUserId(String userId);
}
