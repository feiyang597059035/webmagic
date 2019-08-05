package com.carwel.webmagic.manager.userinterest.fsm.event;


import com.carwel.webmagic.dao.UserInterestDao;
import com.carwel.webmagic.fsm.Order;
import com.carwel.webmagic.fsm.context.Context;
import com.carwel.webmagic.fsm.event.AbstractCreateEvent;
import com.carwel.webmagic.manager.userinterest.fsm.UserInterestFSMOrder;



import com.carwel.webmagic.model.UserInterest;
import com.carwel.webmagic.util.DataConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

;


@Component
public class UserInterestCreateOrderEvent extends AbstractCreateEvent {
     @Autowired
     private UserInterestDao userInterestDao;

    @Override
    public Order buildOrder(Context context) {
        UserInterestFSMOrder order = new UserInterestFSMOrder();
        UserInterest request = (UserInterest) context.lookup("request");
        // build属性
        DataConvertUtils.convertDO2DTO(request,order);
        return order;
    }

    @Override
    public Object createOrder(Order order,Context context) {
        //创建订单
        return userInterestDao.insert((UserInterest) order);

    }

}
