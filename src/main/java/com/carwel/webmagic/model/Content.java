package com.carwel.webmagic.model;

import lombok.Data;

import java.util.Date;
@Data
public class Content {
    private Long id;

    private Integer categoryType;

    private String contentName;

    private Integer finishFlag;

    private Date gmtCreated;

    private Date gmtModifiled;

    private String url;


}