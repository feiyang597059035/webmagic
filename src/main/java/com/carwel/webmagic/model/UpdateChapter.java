package com.carwel.webmagic.model;

import java.util.Date;

public class UpdateChapter {
    private Long id;

    private Integer contentId;

    private Integer chapterNum;

    private String chapterName;

    private Date lastestUpdateTime;

    private Date gmtCreated;

    private Date gmtModifiled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
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

    public Date getLastestUpdateTime() {
        return lastestUpdateTime;
    }

    public void setLastestUpdateTime(Date lastestUpdateTime) {
        this.lastestUpdateTime = lastestUpdateTime;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModifiled() {
        return gmtModifiled;
    }

    public void setGmtModifiled(Date gmtModifiled) {
        this.gmtModifiled = gmtModifiled;
    }
}