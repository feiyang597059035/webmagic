package com.carwel.webmagic.service;

import org.apache.rocketmq.client.producer.SendResult;

public interface SendMQService {
    /**
     * 发送mq 消息
     * @param topic
     * @param tag
     * @param msg
     * @return
     */
   SendResult sendMQMessages(String topic, String tag, String msg);
}
