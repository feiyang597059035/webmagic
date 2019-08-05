package com.carwel.webmagic.dao.impl;

import com.carwel.webmagic.dao.UserInterestDao;
import com.carwel.webmagic.mapper.UserInterestMapper;
import com.carwel.webmagic.model.UserInterest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public class UserInterestDaoImpl implements UserInterestDao {
    @Autowired
    private UserInterestMapper userInterestMapper;
    @Override
    public int insert(UserInterest record) {
        return userInterestMapper.insert(record);
    }

    @Override
    public List<UserInterest> getListUserInterestByUserId(String userId) {
        return userInterestMapper.getListUserInterestByUserId(userId);
    }

    @Override
    public int updateStatus(Long id, Integer currStatus, Integer tarStatus) {
        return userInterestMapper.updateStatus(id,currStatus,tarStatus,new Date());
    }

    @Override
    public UserInterest getUserInterestById(Long id) {
        return userInterestMapper.selectByPrimaryKey(id);
    }
}
