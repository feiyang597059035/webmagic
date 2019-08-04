package com.carwel.webmagic.controller;

import com.carwel.webmagic.config.resultcode.Result;
import com.carwel.webmagic.dto.ChapterESInfoDTO;
import com.carwel.webmagic.manager.ESManager;
import com.carwel.webmagic.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;
    @PostMapping(value = "/getChapterESInfoDTOByContentId",produces = "application/json;charset=UTF-8")
    public Result<ChapterESInfoDTO> getChapterESInfoDTOByContentId(Integer contentId, Integer chapterNum ){
        return  chapterService.getChapterESInfoDTOByContentId(contentId,chapterNum);
    }
}
