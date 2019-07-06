package com.carwel.webmagic.model;

import java.util.Date;

public class Chapter {
    private Long id;

    private Integer chapterNum;

    private String chapterName;

    private String chapterContext;

    private Date gmtCreated;

    private Date gmtModifild;

    private Long contentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(Integer chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName == null ? null : chapterName.trim();
    }

    public String getChapterContext() {
        return chapterContext;
    }

    public void setChapterContext(String chapterContext) {
        this.chapterContext = chapterContext == null ? null : chapterContext.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModifild() {
        return gmtModifild;
    }

    public void setGmtModifild(Date gmtModifild) {
        this.gmtModifild = gmtModifild;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }
}