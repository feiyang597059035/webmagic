package com.carwel.webmagic.service.impl;

import com.carwel.webmagic.dto.SpiderInfoDTO;
import com.carwel.webmagic.manager.SpiderManager;
import com.carwel.webmagic.service.SpiderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SpiderServiceImpl implements SpiderService {
    @Autowired
    private SpiderManager spiderManager;
    /**
     * 抓取jianlai 文章
     *
     * @param spiderInfoDTO
     * @return
     */
    @Override
    public boolean spideJianlai(SpiderInfoDTO spiderInfoDTO) {
       return spiderManager.spideJianlai(spiderInfoDTO);
    }
}
