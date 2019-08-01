package com.carwel.webmagic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigConstant {
    //es topic
    private static  String esTopic;

    public static String getEsTopic() {
        return esTopic;
    }
    @Value("${apache.rocketmq.topic.es_topic}")
    public  void setEsTopic(String esTopic) {
        ConfigConstant.esTopic = esTopic;
    }


    //es  index
    private static  String esChapterIndex;

    //es chapter index 的type
    private static  String esChapterType;

    public static String getEsChapterIndex() {
        return esChapterIndex;
    }
    @Value("${elasticsearch.index.chapter_index}")
    public  void setEsChapterIndex(String esChapterIndex) {
        ConfigConstant.esChapterIndex = esChapterIndex;
    }

    public static String getEsChapterType() {
        return esChapterType;
    }
    @Value("${elasticsearch.type.chapter_type}")
    public  void setEsChapterType(String esChapterType) {
        ConfigConstant.esChapterType = esChapterType;
    }
}
