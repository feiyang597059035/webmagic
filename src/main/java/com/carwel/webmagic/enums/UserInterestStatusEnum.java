package com.carwel.webmagic.enums;


import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserInterestStatusEnum implements  Status {

    CREATED("创建",0,"待审核"),
    SUCCESS("审核通过",10,"审核通过"),
    FAIL("审核不通过",15,"审核不通过"),
    ;

    private String name;

    private int value;


    //前端展示名称
    private String frontDisplayName;

    UserInterestStatusEnum(String name, int value, String frontDisplayName) {
        this.name = name;
        this.value = value;
        this.frontDisplayName=frontDisplayName;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getFrontDisplayName(){
        return frontDisplayName;
    }

    public void setFrontDisplayName(String frontDisplayName) {
        this.frontDisplayName = frontDisplayName;
    }


    public static Status getByCode(int code) {
        for(UserInterestStatusEnum s : UserInterestStatusEnum.values()){
            if(s.getValue() == code){
                return s;
            }
        }
        return null;
    }

    public static String getFrontDisplayName(int code){
        for(UserInterestStatusEnum s : UserInterestStatusEnum.values()){
            if(s.getValue() == code){
                return s.getFrontDisplayName();
            }
        }
        return null;
    }

}
