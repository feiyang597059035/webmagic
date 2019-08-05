package com.carwel.webmagic.fsm.async;


/**
 * 异步接口，暂不实现
 * */
public interface Asynchronizer {

    void call();

    void callBack(Object ret);

}
