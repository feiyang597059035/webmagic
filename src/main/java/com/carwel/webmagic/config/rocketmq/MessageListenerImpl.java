package com.carwel.webmagic.config.rocketmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.carwel.webmagic.config.BeanContext;
import com.carwel.webmagic.dto.SpiderInfoDTO;
import com.carwel.webmagic.manager.SpiderManager;
import com.carwel.webmagic.manager.impl.SpiderManagerImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;


import java.util.List;

public class MessageListenerImpl implements MessageListenerConcurrently {
    SpiderManager spiderManager= BeanContext.getBean(SpiderManagerImpl.class);
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        // TODO Auto-generated method stub
        try {
            for (MessageExt messageExt : msgs) {

                if ("jianlai".equals(messageExt.getTags())){
                    String messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                    if (StringUtils.isNotBlank(messageBody)){
                        JSONObject jsonObject=JSON.parseObject(messageBody);
                        SpiderInfoDTO spiderInfoDTO= JSON.toJavaObject(jsonObject,SpiderInfoDTO.class);
                        spiderManager.spideJianlai(spiderInfoDTO);
                    }
                }
                System.out.println(Thread.currentThread().getName());
                System.out.println("messageExt: " + messageExt);// 输出消息内容

                String messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);

                System.out.println("111消费响应：msgId : " + messageExt.getMsgId() + ", msgBody : " + messageBody);//
                // 输出消息内容
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ConsumeConcurrentlyStatus.RECONSUME_LATER; // 稍后再试
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; // 消费成功
    }

}
