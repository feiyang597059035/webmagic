package com.carwel.webmagic.dto;

import lombok.Data;

@Data
public class SpiderInfoDTO extends  BaseDomain{
    /**抓取url**/
    private String url;
    /**开始页数**/
    private Integer pageNum;
    /**开始条数**/
    private Integer num;
    /**内容id**/
    private Integer contentId;
}
