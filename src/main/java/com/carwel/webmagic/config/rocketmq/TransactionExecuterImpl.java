/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.carwel.webmagic.config.rocketmq;


import com.alibaba.fastjson.JSON;
import com.carwel.webmagic.config.BeanContext;
import com.carwel.webmagic.manager.MessageCheckManager;
import com.carwel.webmagic.manager.impl.MessageCheckManagerImpl;
import com.carwel.webmagic.model.MessageCheck;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


/**
 * 本地事物执行器
 * 
 * @author luog
 * @date 2018年7月20日
 */

@Component
@Slf4j
public class TransactionExecuterImpl implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        return null;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {

        log.info("回查mq:"+new Date());
        log.info("checkLocalTransaction messageExt={}", JSON.toJSONString(messageExt));
        LocalTransactionState localTransactionState=LocalTransactionState.UNKNOW;
        String messageId=messageExt.getTransactionId();
        MessageCheck messageCheck=new MessageCheck();
        messageCheck.setMessageId(messageId);
        MessageCheckManager messageCheckManager= BeanContext.getBean(MessageCheckManagerImpl.class);
        List<MessageCheck> messageCheckList=messageCheckManager.getListMessageCheck(messageCheck);
        if (CollectionUtils.isNotEmpty(messageCheckList)){
            localTransactionState=LocalTransactionState.COMMIT_MESSAGE;
        }else {
            if (messageExt.getReconsumeTimes()>=3){
                localTransactionState=LocalTransactionState.ROLLBACK_MESSAGE;
            }

        }
        log.info("checkLocalTransaction messageExt={},result={}", JSON.toJSONString(messageExt),localTransactionState);
        log.info("回查mq:"+new Date());
        return localTransactionState;
    }
}
