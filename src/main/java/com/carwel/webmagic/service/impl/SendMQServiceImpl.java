package com.carwel.webmagic.service.impl;

import com.carwel.webmagic.config.SpiderMQProducer;
import com.carwel.webmagic.service.SendMQService;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMQServiceImpl implements SendMQService {

    @Autowired
    private SpiderMQProducer spiderMQProducer;
    /**
     * 发送mq 消息
     *
     * @param topic
     * @param tag
     * @param msg
     * @return
     */
    @Override
    public SendResult sendMQMessages(String topic, String tag, String msg) {

        return spiderMQProducer.sendMessage(topic,tag,msg);
    }
}
