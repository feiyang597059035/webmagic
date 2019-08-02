package com.carwel.webmagic.manager;

import com.carwel.webmagic.dto.ChapterESInfoDTO;

public interface ESManager {
    /**
     * 根据章节id 更新es
     * @param id
     * @return
     */
    public boolean addESDataByChpterid(Long id);

    /**
     * 根据文档id 查询
     * @param id
     * @return
     */
    public ChapterESInfoDTO getChapterESInfoDTOById(Long id);

    /**
     * 根据contentId 和章节数 查询
     * @param contentId
     * @param chapterNum
     * @return
     */
    public ChapterESInfoDTO getChapterESInfoDTOByContentId(Integer contentId,Integer chapterNum);
}
