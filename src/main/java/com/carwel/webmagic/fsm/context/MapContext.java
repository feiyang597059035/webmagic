package com.carwel.webmagic.fsm.context;



import com.carwel.webmagic.fsm.FSMException;

import java.util.concurrent.ConcurrentHashMap;

public class MapContext extends ConcurrentHashMap implements Context {


    @Override
    public Object lookup() {
        throw new FSMException("this method do not supported!");
    }

    @Override
    public Object lookup(String name) {
        return get(name);
    }

    @Override
    public void bind(Object obj) {
        throw new FSMException("this method do not supported!");
    }

    @Override
    public void bind(String name,Object obj) {
        put(name,obj);
    }
}
