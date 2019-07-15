package com.carwel.webmagic.service.impl;


import com.carwel.webmagic.config.rocketmq.SpiderMQProducerTransaction;
import com.carwel.webmagic.manager.SendMQManager;
import com.carwel.webmagic.service.SendMQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class SendMQServiceImpl implements SendMQService {

    @Autowired
    private SendMQManager sendMQManager;
    /**
     * 发送mq 事务消息
     *
     * @param topic
     * @param tag
     * @param msg
     * @return
     */
    @Override
    public SendResult sendMQTransactionMessage(String topic, String tag, String msg) {
        SendResult sendResult= sendMQManager.sendMQTransactionMessage(topic,tag,msg);
        return  sendResult;

    }

    /**
     * 发送mq 消息
     *
     * @param topic
     * @param tag
     * @param msg
     * @return
     */
    @Override
    public SendResult sendMessage(String topic, String tag, String msg) {
        SendResult sendResult= sendMQManager.sendMessage(topic,tag,msg);
        return  sendResult;
    }

    /**
     * 事务消息是否回滚
     *
     * @param sendResult
     * @param messageResultType mq事务消息  MQResultType
     * @return
     */
    @Override
    public boolean endTransactionMessage(SendResult sendResult, Integer messageResultType) {
        sendMQManager.endTransactionMessage(sendResult, messageResultType);
        return true;
    }


}
