package com.carwel.webmagic.model;

import java.util.Date;

public class User {
    private Long id;

    private String userName;

    private String userId;

    private Date gmtCreated;

    private Date gmtModifiled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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