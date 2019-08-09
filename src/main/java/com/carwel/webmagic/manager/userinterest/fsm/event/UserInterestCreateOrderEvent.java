package com.carwel.webmagic.manager.userinterest.fsm.event;


import com.carwel.webmagic.config.resultcode.BusinessException;
import com.carwel.webmagic.config.resultcode.CodeMsg;
import com.carwel.webmagic.dao.ContentDao;
import com.carwel.webmagic.dao.UserInterestDao;
import com.carwel.webmagic.enums.UserInterestStatusEnum;
import com.carwel.webmagic.fsm.Order;
import com.carwel.webmagic.fsm.context.Context;
import com.carwel.webmagic.fsm.event.AbstractCreateEvent;
import com.carwel.webmagic.manager.userinterest.fsm.UserInterestFSMOrder;


import com.carwel.webmagic.model.Content;
import com.carwel.webmagic.model.UserInterest;
import com.carwel.webmagic.request.CreateUserInterestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

;import java.util.Date;


@Component
public class UserInterestCreateOrderEvent extends AbstractCreateEvent {
     @Autowired
     private UserInterestDao userInterestDao;
     @Autowired
     private ContentDao contentDao;

    @Override
    public Order buildOrder(Context context) {
        UserInterestFSMOrder order = new UserInterestFSMOrder();
        CreateUserInterestRequest request = (CreateUserInterestRequest) context.lookup("request");
        // build属性
        order.setFSMStatus(UserInterestStatusEnum.CREATED);
        order.setDeleteFlag(0);
        order.setGmtCreated(new Date());
        order.setGmtModifiled(new Date());
        order.setUserId(request.getUserId());
        order.setCategoryType(request.getCategoryType());
        return order;
    }

    @Override
    public Object createOrder(Order order,Context context) {
        //TODO  content表并发
        CreateUserInterestRequest request = (CreateUserInterestRequest) context.lookup("request");
        String contentName=request.getContentName();
        Content content=contentDao.getContentByContentName(contentName.replace(" ",""));
        Long contentId=0L;
        if (content!=null&&content.getId().longValue()>0){
            contentId=content.getId();
        }else {
           //创建content 内容表
            content=coverContentDO(request);
            //插入content
            contentId=  contentDao.insert(content);
        }
        if (contentId==null||contentId.longValue()<0){
            throw new BusinessException(CodeMsg.SERVER_ERROR);
        }
        UserInterest userInterest=(UserInterest)order;
        userInterest.setContentId(contentId);
        //创建订单
        return (Object)userInterestDao.insert(userInterest);

    }

    private Content coverContentDO(CreateUserInterestRequest createUserInterestRequest){
        Content content=new Content();
        content.setCategoryType(createUserInterestRequest.getCategoryType());
        content.setContentName(createUserInterestRequest.getContentName());
        content.setFinishFlag(0);
        content.setGmtCreated(new Date());
        content.setGmtModifiled(new Date());
        content.setUrl(createUserInterestRequest.getUrl());
        return  content;
    }

}
