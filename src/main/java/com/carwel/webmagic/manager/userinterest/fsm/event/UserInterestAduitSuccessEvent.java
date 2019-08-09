package com.carwel.webmagic.manager.userinterest.fsm.event;


import com.carwel.webmagic.config.resultcode.BusinessException;
import com.carwel.webmagic.config.resultcode.CodeMsg;
import com.carwel.webmagic.fsm.context.Context;
import com.carwel.webmagic.fsm.event.AbstractUpdateEvent;
import com.carwel.webmagic.manager.ContentManager;
import com.carwel.webmagic.manager.userinterest.fsm.UserInterestFSMOrder;

import com.carwel.webmagic.model.Content;
import com.carwel.webmagic.request.CreateUserInterestRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInterestAduitSuccessEvent extends AbstractUpdateEvent<UserInterestFSMOrder> {
    @Autowired
    private ContentManager contentManager;

    @Override
    public Object doUpdate(UserInterestFSMOrder order, Context context) {
        CreateUserInterestRequest createUserInterestRequest = (CreateUserInterestRequest) context.lookup("request");
        if (createUserInterestRequest!=null&& StringUtils.isNotBlank(createUserInterestRequest.getUrl())){
            Content content=new Content();
            content.setId(order.getContentId());
            content.setUrl(createUserInterestRequest.getUrl());
            int response=contentManager.updateContentById(content);
            if (response<=0){
                throw  new BusinessException(CodeMsg.SERVER_ERROR);
            }
        }
        return 1;

    }
}
