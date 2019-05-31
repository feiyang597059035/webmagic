package com.carwel.webmagic.controller;

import com.carwel.webmagic.dto.SpiderInfoDTO;
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

    @RequestMapping("/test")
    public void  test(){
        try {
            itemTask.itemTask();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("/jianlaiSpider")
    public  boolean  jianLaiSpider(@RequestBody SpiderInfoDTO spiderInfoDTO){
        boolean result=spiderService.spideJianlai(spiderInfoDTO);
        return  false;
    };
}
