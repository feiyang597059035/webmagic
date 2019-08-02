package com.carwel.webmagic.dto;

import lombok.Data;



@Data
public class ChapterESInfoDTO extends  BaseDomain {
    private Long id;
    private String gmtModifild;
    /**
     * 章节名称
     */
    private String chapterName;
    /**
     * 章节内容
     */
    private String chapterContext;
    private Integer  contentId;
    /**章节**/
    private Integer chapterNum;
    /**
     * 分类
     */
    private Integer categoryType;

    private String categoryName;
    /**
     * 文章名称
     */
    private String contentName;
}
