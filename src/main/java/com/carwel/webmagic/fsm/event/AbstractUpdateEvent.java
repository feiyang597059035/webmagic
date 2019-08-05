package com.carwel.webmagic.fsm.event;

import com.carwel.webmagic.fsm.FSMException;
import com.carwel.webmagic.fsm.Order;
import com.carwel.webmagic.fsm.context.Context;


public abstract class AbstractUpdateEvent<T extends Order> implements Event{

    public abstract Object doUpdate(T order, Context context);

    /**
     * order的status已经被状态机跳转到目的状态，event无需更新状态字段，也查不到前面的状态值
     */
    @Override
    public Object doEvent(Order order, Context context) {
        T orderT;
        try{
            orderT = (T)order;
        }catch (ClassCastException c){
            throw new FSMException("order object is not a instance of order ");
        }
        Object ret = doUpdate(orderT, context);
        return ret;
    }

}
