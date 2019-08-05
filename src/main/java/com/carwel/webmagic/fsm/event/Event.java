package com.carwel.webmagic.fsm.event;


import com.carwel.webmagic.fsm.Order;
import com.carwel.webmagic.fsm.context.Context;

public interface Event {

    /**
     * 这里可以操作一些rpc、本地更新等操作
     * PS：切记如果操作其他表的乐观锁，可能会造成死锁，或按照业务表大小顺序依次获取锁避免
     * 操作中也请勿查询锁字段，因为已经字段已经被上一步updateStatus()流转掉了
     * */
    Object doEvent(Order order, Context context);

}
