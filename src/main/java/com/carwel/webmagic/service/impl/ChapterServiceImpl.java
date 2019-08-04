package com.carwel.webmagic.service.impl;

import com.carwel.webmagic.config.resultcode.CodeMsg;
import com.carwel.webmagic.config.resultcode.Result;
import com.carwel.webmagic.dto.ChapterESInfoDTO;
import com.carwel.webmagic.manager.ESManager;
import com.carwel.webmagic.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ESManager esManager;
    /**
     * 根据内容id 和章节获取信息
     *
     * @param contentId
     * @param chapterNum
     * @return
     */
    @Override
    public Result<ChapterESInfoDTO> getChapterESInfoDTOByContentId(Integer contentId, Integer chapterNum) {
        ChapterESInfoDTO chapterESInfoDT=esManager.getChapterESInfoDTOByContentId(contentId,chapterNum);
        if (chapterESInfoDT!=null){
            return  Result.write(CodeMsg.SUCCESS,chapterESInfoDT);
        }
        return Result.error(CodeMsg.DATO_NOT_EXIST);
    }
}
