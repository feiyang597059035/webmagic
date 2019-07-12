package com.carwel.webmagic.dto.Enum;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.LinkedList;
import java.util.List;

public enum MQResultTypeEnum {

    COMMIT_MESSAGE("提交消息", 1),
    ROLLBACK_MESSAGE("回滚消息", 2),
    UNKNOW("未知", 3),
    ;
    private String name;

    private int value;

    MQResultTypeEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static MQResultTypeEnum getEnum(String name) {
        try {
            return valueOf(name);
        } catch (Exception e) {
            return null;
        }
    }


    public static MQResultTypeEnum getEnum(Integer value) {
        if (value == null) {
            return null;
        }

        for (MQResultTypeEnum item : MQResultTypeEnum.values()) {
            if (item.getValue()==value.intValue()) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "MQResultTypeEnum{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }


}
