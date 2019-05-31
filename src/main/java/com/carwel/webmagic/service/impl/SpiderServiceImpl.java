package com.carwel.webmagic.service.impl;

import com.carwel.webmagic.dto.SpiderInfoDTO;
import com.carwel.webmagic.service.SpiderService;
import com.carwel.webmagic.task.jianlai.JianlaiJobProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

@Service
public class SpiderServiceImpl implements SpiderService {
    @Autowired
    private JianlaiJobProcessor jianlaiJobProcessor;
    /**
     * 抓取jianlai 文章
     *
     * @param spiderInfoDTO
     * @return
     */
    @Override
    public boolean spideJianlai(SpiderInfoDTO spiderInfoDTO) {
        jianlaiJobProcessor.startJianlaiJob(spiderInfoDTO);
        return false;
    }
}
