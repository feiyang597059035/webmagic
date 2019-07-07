package com.carwel.webmagic.dto;

import lombok.Data;

@Data
public class ChapterInfoDTO extends BaseDomain{
    private String updateTime;
    private String chapterName;
    private String chapterContext;
    private Integer  contentId;
    /**章节**/
    private Integer chapterNum;
}
