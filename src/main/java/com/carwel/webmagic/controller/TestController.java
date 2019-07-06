package com.carwel.webmagic.controller;

import com.carwel.webmagic.dao.TestDao;
import com.carwel.webmagic.dto.SpiderInfoDTO;

import com.carwel.webmagic.model.test;
import com.carwel.webmagic.testtask.xiaoshuoJianLaiTask;
import com.carwel.webmagic.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {
    @Autowired
    private xiaoshuoJianLaiTask itemTask;
    @Autowired
    private SpiderService spiderService;
    @Autowired
    private TestDao testDao;

    @RequestMapping("/test")
    public test test(){
    test test=testDao.selectByPrimaryKey(1);
    return test;

    }
    @RequestMapping("/jianlaiSpider")
    public  boolean  jianLaiSpider(@RequestBody SpiderInfoDTO spiderInfoDTO){
        boolean result=spiderService.spideJianlai(spiderInfoDTO);
        return  false;
    };
}
