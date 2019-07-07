package com.carwel.webmagic.model;

import lombok.Data;

import java.util.Date;
@Data
public class Chapter {
    private Long id;

    private Integer chapterNum;

    private String chapterName;

    private String chapterContext;

    private Date gmtCreated;

    private Date gmtModifild;

    private Integer contentId;


}