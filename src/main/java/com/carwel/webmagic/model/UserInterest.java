package com.carwel.webmagic.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserInterest {
    private Long id;

    private String userId;

    private Long contentId;

    private Integer deleteFlag;

    private Integer categoryType;

    private Date gmtCreated;

    private Date gmtModifiled;

    private Integer status;


}