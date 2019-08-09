package com.carwel.webmagic.controller;

import com.carwel.webmagic.config.resultcode.Result;
import com.carwel.webmagic.request.CreateUserInterestRequest;
import com.carwel.webmagic.service.UserInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 添加个人兴趣收藏
 */
@RestController
@RequestMapping("/userinterest")
public class UserInterestController {

    @Autowired
    private UserInterestService userInterestService;

    @PostMapping(value = "/addUserInterest",produces = "application/json;charset=UTF-8")
    public Result<Boolean> addUserInterest(@RequestBody CreateUserInterestRequest createUserInterestRequest){
        return  userInterestService.addUserInterest(createUserInterestRequest);
    }

}
