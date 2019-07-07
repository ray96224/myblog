package com.ray.myblog.dao;

import com.ray.myblog.entity.ArticleImage;
import com.ray.myblog.entity.ArticleImageExample;
import java.util.List;

public interface ArticleImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleImage record);

    int insertSelective(ArticleImage record);

    List<ArticleImage> selectByExample(ArticleImageExample example);

    ArticleImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleImage record);

    int updateByPrimaryKey(ArticleImage record);
}