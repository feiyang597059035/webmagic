package com.carwel.webmagic.fsm.context;

/**
 * 基本的情况下可以当request用，此时使用ObjectContext
 * 也可以穿梭于fsm的各个方法
 * */
public interface Context {

    Object lookup();

    Object lookup(String name);

    /**
     * 可重复绑定,会覆盖
     * */
    void bind(Object obj);

    /**
     * 可重复绑定,会覆盖
     * */
    void bind(String name, Object obj);

}
