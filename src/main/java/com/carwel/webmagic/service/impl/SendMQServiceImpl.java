package com.carwel.webmagic.service.impl;


import com.carwel.webmagic.config.rocketmq.SpiderMQProducerTransaction;
import com.carwel.webmagic.service.SendMQService;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMQServiceImpl implements SendMQService {

    @Autowired
    private SpiderMQProducerTransaction spiderMQProducerTransaction;
    /**
     * 发送mq 事务消息
     *
     * @param topic
     * @param tag
     * @param msg
     * @return
     */
    @Override
    public SendResult sendMQTransactionMessage(String topic, String tag, String msg,Integer type) {

        SendResult sendResult= spiderMQProducerTransaction.sendMessage(topic,tag,msg);
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
        spiderMQProducerTransaction.endTransactionMessage(sendResult, messageResultType);
        return true;
    }


}
