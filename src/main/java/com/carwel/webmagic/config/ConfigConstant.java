package com.carwel.webmagic.config;

import org.springframework.beans.factory.annotation.Value;

public class ConfigConstant {
    //es topic
    private static  String esTopic;

    public static String getEsTopic() {
        return esTopic;
    }
    @Value("${apache.rocketmq.topic.es}")
    public  void setEsTopic(String esTopic) {
        ConfigConstant.esTopic = esTopic;
    }
}
