package com.carwel.webmagic.service;

import com.carwel.webmagic.dto.SpiderInfoDTO;

public interface SpiderService {
    /**
     * 抓取jianlai 文章
     * @param spiderInfoDTO
     * @return
     */
    public  boolean spideJianlai(SpiderInfoDTO spiderInfoDTO);
}
