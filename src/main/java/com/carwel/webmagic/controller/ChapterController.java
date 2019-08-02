package com.carwel.webmagic.controller;

import com.carwel.webmagic.dto.ChapterESInfoDTO;
import com.carwel.webmagic.manager.ESManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ESManager esManager;
    @PostMapping(value = "/getChapterESInfoDTOByContentId",produces = "application/json;charset=UTF-8")
    public ChapterESInfoDTO  getChapterESInfoDTOByContentId(Integer contentId, Integer chapterNum ){
        return  esManager.getChapterESInfoDTOByContentId(contentId,chapterNum);
    }
}
