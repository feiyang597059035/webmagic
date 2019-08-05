package com.carwel.webmagic.model;

import lombok.Data;

@Data
public class UserInterest {
    private Long id;

    private String userId;

    private Long contentId;

    private Integer deleteFlag;

    private Integer categoryType;

    private String gmtCreated;

    private String gmtModifiled;

    private Integer status;


}