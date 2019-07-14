package com.carwel.webmagic.config.rocketmq;

import com.carwel.webmagic.dto.Enum.MQResultTypeEnum;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.net.UnknownHostException;

/**
 * 改造发送事务消息 接口 将发送half 消息 本地方法执行 发送操作状态消息 三者 分离 让程序员自己控制是否消息
 * 
 * @author luog
 * @date 2018年12月1日
 */
public class TransactionMQProducerCustom extends TransactionMQProducer {
    public TransactionMQProducerCustom(String producerGroup) {
        super(producerGroup);
    }

    public void endTransactionMessage(SendResult sendResult,Integer returnType) {
        LocalTransactionState localTransactionState = LocalTransactionState.UNKNOW;
        if (returnType==null){
            localTransactionState=LocalTransactionState.UNKNOW;
        }else if (returnType.intValue()== MQResultTypeEnum.COMMIT_MESSAGE.getValue()){
            localTransactionState=LocalTransactionState.COMMIT_MESSAGE;
        }else if (returnType.intValue()==MQResultTypeEnum.ROLLBACK_MESSAGE.getValue()){
            localTransactionState=LocalTransactionState.ROLLBACK_MESSAGE;
        }

        Throwable localException = new Throwable();
        try {
            this.defaultMQProducerImpl.endTransaction(sendResult, localTransactionState, localException);
        } catch (UnknownHostException e) {
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
    }

}
