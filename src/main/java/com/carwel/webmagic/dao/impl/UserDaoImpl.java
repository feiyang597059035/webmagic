package com.carwel.webmagic.dao.impl;

import com.carwel.webmagic.dao.UserDao;
import com.carwel.webmagic.mapper.UserMapper;
import com.carwel.webmagic.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public User getUserByUserId(String userId) {
        return null;
    }
}
