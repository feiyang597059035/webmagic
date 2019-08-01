package com.carwel.webmagic.manager;

import com.carwel.webmagic.dto.SpiderInfoDTO;

public interface SpiderManager {
    /**
     * 抓取剑来小说
     * @param spiderInfoDTO
     * @return
     */
    public  boolean spideJianlai(SpiderInfoDTO spiderInfoDTO);
}
