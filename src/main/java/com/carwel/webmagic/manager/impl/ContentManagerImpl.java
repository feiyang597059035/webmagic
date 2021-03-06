package com.carwel.webmagic.manager.impl;


import com.carwel.webmagic.dao.ContentDao;
import com.carwel.webmagic.manager.ContentManager;
import com.carwel.webmagic.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ContentManagerImpl implements ContentManager {
    @Autowired
    private ContentDao contentDao;
    @Override
    public Content getContentById(Long id) {
        return contentDao.getContentById(id);
    }

    /**
     * 更新内容
     *
     * @param content
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int updateContentById(Content content) {
        return contentDao.updateContentById(content);
    }
}
