package com.carwel.webmagic.dao;

import com.carwel.webmagic.model.test;

public interface TestDao {
    test selectByPrimaryKey(Integer id);

}
