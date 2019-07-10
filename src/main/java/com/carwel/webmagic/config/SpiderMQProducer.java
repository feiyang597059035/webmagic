package com.carwel.webmagic.config;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpiderMQProducer {
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

    DefaultMQProducer defaultMQProducer = null;

    private static Logger logger = LoggerFactory.getLogger(SpiderMQProducer.class);

    @PostConstruct
    public void defaultMQProducer() {
        // 生产者的组名
        defaultMQProducer = new DefaultMQProducer(producerGroup);
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

    public SendResult sendMessage(String topic, String tag, String msg) {
        Message message = new Message(topic, tag, msg.getBytes());
        SendResult sendResult = null;
        try {
            sendResult = defaultMQProducer.send(message,10000);

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
}
