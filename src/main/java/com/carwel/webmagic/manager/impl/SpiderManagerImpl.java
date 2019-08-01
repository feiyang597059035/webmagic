package com.carwel.webmagic.manager.impl;

import com.carwel.webmagic.dto.SpiderInfoDTO;
import com.carwel.webmagic.manager.SpiderManager;
import com.carwel.webmagic.task.jianlai.JianlaiJobProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpiderManagerImpl implements SpiderManager {
    @Autowired
    private JianlaiJobProcessor jianlaiJobProcessor;
    /**
     * 抓取剑来小说
     *
     * @param spiderInfoDTO
     * @return
     */
    @Override
    public boolean spideJianlai(SpiderInfoDTO spiderInfoDTO) {
        jianlaiJobProcessor.startJianlaiJob(spiderInfoDTO);
        return true;
    }
}
