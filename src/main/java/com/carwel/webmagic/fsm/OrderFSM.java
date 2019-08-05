package com.carwel.webmagic.fsm;

import com.carwel.webmagic.fsm.context.Context;
import com.carwel.webmagic.fsm.event.Event;


public interface OrderFSM {

    Object onEvent(Order order, Context context, Event event);

}
