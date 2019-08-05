package com.carwel.webmagic.fsm;


import com.carwel.webmagic.config.resultcode.BusinessException;
import com.carwel.webmagic.config.resultcode.CodeMsg;
import com.carwel.webmagic.enums.Status;
import com.carwel.webmagic.fsm.context.Context;
import com.carwel.webmagic.fsm.event.AbstractCreateEvent;
import com.carwel.webmagic.fsm.event.AbstractUpdateEvent;
import com.carwel.webmagic.fsm.event.Event;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public abstract class AbstractOrderFSM implements OrderFSM{

    /**
     * 通过订单表的乐观锁占用订单，需要程序自己实现，也是状态机维度的统一实现，并没有做到event中
     * 此时的order中的状态还是加载时的状态
     * 拿到锁return1，拿不到return0
     * */
    public abstract int updateStatus(Order order, Context context, Status status);

    /**
     * 存在同一张表的订单流转不同的fsm
     * */
    public abstract Map<Status, Map<Class, Status>> findMap(Order order,Context context);

    /**
     * 记录事件日志
     * */
    public abstract void addEventLog(Order order, Context context, Event event);

    /**
     * 不负责订单数据加载，只负责流转部分的业务逻辑，调用之前请勿做写操作升级锁
     * 这里也不负责参数的校验，进入状态机之前或在event中完成校验操作
     * event最好是个bean
     * Order最好使用dao，context
     * */
    @Override
    public Object onEvent(Order order, Context context, Event event){
        Object retValue = null;
        if(event instanceof AbstractUpdateEvent){
            retValue = onUpdate(order,context,event);
        }
        else if(event instanceof AbstractCreateEvent){
            retValue = onCreate(order,context,event);
        }
        addEventLog(order,context,event);
        return retValue;
    }

    private Object onUpdate(Order order, Context context, Event event){
        Object retValue = null;
        //捞取当前状态
        Status status = order.getFSMStatus();
        if(status == null){
            throw new FSMException(CodeMsg.STATUS_NULL_ERROR.getMsg());
        }
        //判断条件，查找fsmMap
        Map<Status, Map<Class, Status>> fsmMap = findMap(order,context);
        //查找目的状态
        Status tarStatus =null;
        if (fsmMap.get(status)!=null){
             tarStatus = fsmMap.get(status).get(event.getClass());
        }

        if(tarStatus == null){
            throw new FSMException(CodeMsg.STATUS_ILLEGAL_ERROR.getMsg());
        }
        //跳转目的状态，占锁
        int getLock  = updateStatus(order,context,tarStatus);
        if(getLock == 1) {
            //执行event
            try {
                order.setFSMStatus(tarStatus);
                retValue = event.doEvent(order,context);
            }catch (Throwable t){
                //执行失败抛出异常回滚跳转
                log.error("event error",t);
                throw new FSMException("do event error !",t);
            }
        }
        else{
            //未抢到锁
            log.info("didnt get lock");
        }
        return retValue;
    }

    private Object onCreate(Order order, Context context, Event event){
        Object retValue = null;
        //判断条件，查找fsmMap
        Map<Status, Map<Class, Status>> fsmMap = findMap(order,context);
        //查找目的状态
        Status tarStatus = fsmMap.get(null).get(event.getClass());
        //插入数据不需要占锁，直接触发event
        try{
            retValue = event.doEvent(order,context);
        }catch (FSMException e){
            log.error("fsm error",e);
            if(e.getErrorCode()==null){
                throw e;
            }
            throw new BusinessException(CodeMsg.SERVER_ERROR);
        }
        return retValue;
    }

}
