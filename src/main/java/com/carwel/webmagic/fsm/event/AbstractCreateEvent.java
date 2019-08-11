package com.carwel.webmagic.fsm.event;


import com.carwel.webmagic.config.resultcode.ErrorCode;
import com.carwel.webmagic.fsm.FSMException;
import com.carwel.webmagic.fsm.Order;
import com.carwel.webmagic.fsm.context.Context;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
public abstract class AbstractCreateEvent implements Event{

    /**
     * 构建订单，需要自己set status
     * */
    public abstract Order buildOrder(Context context);

    /**
     * 持久化订单，抛出db异常
     * */
    public abstract Object createOrder(Order order,Context context);

    /**
     * 插入订单时状态机并没有对订单表做锁，因此这里需要event实现幂等
     * */
    @Override
    public Object doEvent(Order order, Context context) {

        try{
            Order orderBuilded = buildOrder(context);
            Object ret = createOrder(orderBuilded,context);
            return ret;
        }catch (Throwable t){
            log.error("create order error:",t);
            //处理duplicatedKey
            if(t instanceof DuplicateKeyException){
                log.error("触发幂等,{}",order,t);
                //抛fsm 异常后 系统直接处理掉了 改成TradeBusinessException
               throw new FSMException(ErrorCode.DUPLICATE_ERROR.getMessage());

            }else{
                throw t;
            }
        }
    }

}
