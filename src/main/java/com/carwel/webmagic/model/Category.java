package com.carwel.webmagic.model;

import java.util.Date;

public class Category {
    private Long id;

    private Integer categoryType;

    private String categoryName;

    private Date gmtCreated;

    private Date gmtModifiled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
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