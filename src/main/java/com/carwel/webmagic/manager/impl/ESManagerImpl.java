package com.carwel.webmagic.manager.impl;

import com.alibaba.fastjson.JSONObject;
import com.carwel.webmagic.config.ConfigConstant;
import com.carwel.webmagic.dto.ChapterESInfoDTO;
import com.carwel.webmagic.manager.ChapterManager;
import com.carwel.webmagic.manager.ESManager;
import com.carwel.webmagic.util.ElasticsearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ESManagerImpl implements ESManager {
    @Autowired
    private ChapterManager chapterManager;
    /**
     * 根据章节id 更新es
     *
     * @param id
     * @return
     */
    @Override
    public boolean addESDataByChpterid(Long id) {
        ChapterESInfoDTO chapterESInfoDTO=chapterManager.getChapterESInfoByChapterId(id);
        if (chapterESInfoDTO!=null){
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(chapterESInfoDTO);
          String responId=  ElasticsearchUtil.addData(jsonObject, ConfigConstant.getEsChapterIndex(),
                    ConfigConstant.getEsChapterType(),id.toString());
          String ss=responId;

        }
        return true;
    }
}
