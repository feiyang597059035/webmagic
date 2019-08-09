package com.carwel.webmagic.manager;

import com.carwel.webmagic.model.Content;

public interface ContentManager {
    Content getContentById(Long id);

    /**
     * 更新内容
     * @param content
     * @return
     */
    int updateContentById(Content content);
}
