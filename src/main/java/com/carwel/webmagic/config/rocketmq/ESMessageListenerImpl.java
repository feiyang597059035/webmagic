package com.carwel.webmagic.config.rocketmq;


import com.carwel.webmagic.config.BeanContext;

import com.carwel.webmagic.manager.ESManager;

import com.carwel.webmagic.manager.impl.ESManagerImpl;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

public class ESMessageListenerImpl implements MessageListenerConcurrently {

    ESManager esManager=BeanContext.getBean(ESManagerImpl.class);
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        // TODO Auto-generated method stub
        try {
            for (MessageExt messageExt : msgs) {
                if (messageExt.getReconsumeTimes()>3){
                    //记录失败的日志
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; // 消费成功
                }
                String messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                if("es".equals(messageExt.getTags())){
                   Long chapterId=Long.valueOf(messageBody);
                   esManager.addESDataByChpterid(chapterId);
                }

                // 输出消息内容
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ConsumeConcurrentlyStatus.RECONSUME_LATER; // 稍后再试
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; // 消费成功
    }

}
