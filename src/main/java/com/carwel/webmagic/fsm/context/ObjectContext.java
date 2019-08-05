package com.carwel.webmagic.fsm.context;


import com.carwel.webmagic.fsm.FSMException;

public class ObjectContext implements Context {

    private Object obj;

    public ObjectContext(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object lookup() {
        return this.obj;
    }

    @Override
    public Object lookup(String name) {
        throw new FSMException("this method do not supported!");
    }

    @Override
    public void bind(Object obj) {
        this.obj = obj;
    }

    @Override
    public void bind(String name, Object obj) {
        throw new FSMException("this method do not supported!");
    }
}
