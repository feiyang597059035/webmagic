package com.carwel.webmagic.manager.impl;

import com.alibaba.fastjson.JSONObject;
import com.carwel.webmagic.config.ConfigConstant;
import com.carwel.webmagic.dto.ChapterESInfoDTO;
import com.carwel.webmagic.manager.ChapterManager;
import com.carwel.webmagic.manager.ESManager;
import com.carwel.webmagic.util.ElasticsearchUtil;
import com.carwel.webmagic.util.MapTool;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
          if(StringUtils.isBlank(responId)){
              return  false;
          }

        }
        return true;
    }

    @Override
    public ChapterESInfoDTO getChapterESInfoDTOById(Long id) {
        ChapterESInfoDTO chapterESInfoDTO=null;
        Map<String, Object> result= ElasticsearchUtil.searchDataById(ConfigConstant.getEsChapterIndex(),
                ConfigConstant.getEsChapterType(), id.toString(),"");
        if (result!=null&&result.size()>0){
             chapterESInfoDTO= MapTool.map2Bean(result,ChapterESInfoDTO.class);
        }
        return chapterESInfoDTO;
    }

    /**
     * 根据contentId 和章节数 查询
     *
     * @param contentId
     * @param chapterNum
     * @return
     */
    @Override
    public ChapterESInfoDTO getChapterESInfoDTOByContentId(Integer contentId, Integer chapterNum) {
        ChapterESInfoDTO chapterESInfoDTO=null;
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.termQuery("contentId",contentId)).must(QueryBuilders.termQuery("chapterNum",chapterNum));
        List<Map<String, Object>> list=ElasticsearchUtil.searchListData(ConfigConstant.getEsChapterIndex(),
                ConfigConstant.getEsChapterType(),boolQuery,1,null,null,null);
        if (CollectionUtils.isNotEmpty(list)){
            chapterESInfoDTO=  MapTool.map2Bean(list.get(0),ChapterESInfoDTO.class);
        }
        return  chapterESInfoDTO;

    }
}
