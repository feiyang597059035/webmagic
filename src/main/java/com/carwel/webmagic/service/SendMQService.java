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
   SendResult sendMQTransactionMessage(String topic, String tag, String msg);


    /**
     * 发送mq 消息
     * @param topic
     * @param tag
     * @param msg
     * @return
     */
    SendResult sendMessage(String topic, String tag, String msg);
    /**
     * 事务消息是否回滚
     * @param sendResult
     * @param messageResultType  mq事务消息  MQResultType
     * @return
     */
   boolean endTransactionMessage(SendResult sendResult,Integer messageResultType);
}
