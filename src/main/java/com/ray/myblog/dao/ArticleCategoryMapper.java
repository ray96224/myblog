package com.ray.myblog.dao;

import com.ray.myblog.entity.ArticleCategory;
import com.ray.myblog.entity.ArticleCategoryExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface ArticleCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCategory record);

    int insertSelective(ArticleCategory record);

    List<ArticleCategory> selectByExample(ArticleCategoryExample example);

    ArticleCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleCategory record);

    int updateByPrimaryKey(ArticleCategory record);
}