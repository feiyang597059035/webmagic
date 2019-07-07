/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.carwel.webmagic.dto;



import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author pro
 * @version V1.0
 * @since 2019-02-13 17:38
 */
public class BaseDomain implements Serializable {

    private static final long serialVersionUID = 7975161502039864950L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
