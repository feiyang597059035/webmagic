package com.carwel.webmagic.manager.impl;

import com.carwel.webmagic.config.rocketmq.SpiderMQProducerTransaction;
import com.carwel.webmagic.manager.MessageCheckManager;
import com.carwel.webmagic.manager.SendMQManager;
import com.carwel.webmagic.model.MessageCheck;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class SendMQManagerImpl implements SendMQManager {
    @Autowired
    private MessageCheckManager messageCheckManager;
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
    public SendResult sendMQTransactionMessage(String topic, String tag, String msg) {
        SendResult sendResult= spiderMQProducerTransaction.sendMessage(topic,tag,msg);
        if(sendResult!=null&&sendResult.getSendStatus()== SendStatus.SEND_OK){
            //保存消息
            MessageCheck messageCheck=new MessageCheck();
            messageCheck.setMessageId(sendResult.getMsgId());
            messageCheck.setGmtCreated(new Date());
            messageCheck.setGmtModifiled(new Date());
            messageCheckManager.insert(messageCheck);
        }
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
        return spiderMQProducerTransaction.sendMessage(topic,tag,msg);
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
