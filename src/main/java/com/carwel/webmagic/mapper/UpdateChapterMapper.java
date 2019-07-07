package com.carwel.webmagic.mapper;

import com.carwel.webmagic.model.UpdateChapter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpdateChapterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UpdateChapter record);

    int insertSelective(UpdateChapter record);

    UpdateChapter selectByPrimaryKey(Long id);

    UpdateChapter getUpdateChapterByContentId(Integer contentId);

    int updateByPrimaryKeySelective(UpdateChapter record);

    int updateByPrimaryKey(UpdateChapter record);
}