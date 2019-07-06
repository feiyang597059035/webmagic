package com.carwel.webmagic.dao.impl;

import com.carwel.webmagic.dao.TestDao;
import com.carwel.webmagic.mapper.testMapper;
import com.carwel.webmagic.model.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao {
    @Autowired
    private testMapper testMapper;
    @Override
    public test selectByPrimaryKey(Integer id) {
        return testMapper.selectByPrimaryKey(id);
    }
}
