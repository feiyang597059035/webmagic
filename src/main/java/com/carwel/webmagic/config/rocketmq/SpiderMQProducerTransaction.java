package com.carwel.webmagic.config.rocketmq;


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageAccessor;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 产生事物消息
 * 
 * @author luog
 * @date 2018年7月20日
 */
@Component
public class SpiderMQProducerTransaction {
    /**
     * 生产者的组名
     */
    @Value("${apache.rocketmq.producer.spiderproducerGroup}")
    private String producerGroup;

    /**
     * NameServer 地址
     */
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    TransactionMQProducerCustom defaultMQProducer = null;

    private static Logger logger = LoggerFactory.getLogger(SpiderMQProducer.class);

    @PostConstruct
    public void defaultMQProducer() {
        // 生产者的组名
        defaultMQProducer = new TransactionMQProducerCustom(producerGroup);

        // 指定NameServer地址，多个地址以 ; 隔开
        defaultMQProducer.setNamesrvAddr(namesrvAddr);


        //设置消息 会查类
        TransactionListener transactionListener = new TransactionExecuterImpl();
        defaultMQProducer.setTransactionListener(transactionListener);

        // 指定NameServer地址，多个地址以 ; 隔开
        defaultMQProducer.setNamesrvAddr(namesrvAddr);

        try {
            System.out.println("启动生产者开始");
            logger.info("启动生产者开始");
            defaultMQProducer.start();
            logger.info("启动生产者完成");
        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public  SendResult sendMessage(String topic, String tag, String msg){
        Message message = new Message(topic, tag, msg.getBytes());
        SendResult sendResult = null;
        try {
            sendResult = defaultMQProducer.send(message, 10000);

            logger.info("生产者" + sendResult.toString());

        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemotingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MQBrokerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sendResult;
    }

    public SendResult sendTransactionMessage(String topic, String tag, String msg) {
        Message message = new Message(topic, tag, msg.getBytes());
        SendResult sendResult = null;
        try {
            MessageAccessor.putProperty(message, MessageConst.PROPERTY_TRANSACTION_PREPARED, "true");
            MessageAccessor.putProperty(message, MessageConst.PROPERTY_PRODUCER_GROUP, this.defaultMQProducer.getProducerGroup());
            sendResult = defaultMQProducer.send(message, 10000);

            logger.info("生产者" + sendResult.toString());

        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemotingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MQBrokerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sendResult;
    }

    /**
     * 发送消息结果  是否回滚提交 事务消息
     * @param sendResult
     * @param returnType
     */
    public void endTransactionMessage(SendResult sendResult,Integer returnType) {
        defaultMQProducer.endTransactionMessage(sendResult,returnType);
    }


}