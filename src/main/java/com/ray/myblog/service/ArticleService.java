package com.ray.myblog.service;

import com.ray.myblog.dto.ArticleDto;
import com.ray.myblog.dto.ArticleSimpleDto;

import java.util.List;

/**
 * @author: ray
 * @create: 2019/7/7
 */
public interface ArticleService {

    /**
     * 添加一篇文章
     * @param articleDto 详细文章dto
     */
    void addArticle(ArticleDto articleDto);

    /**
     * 查询一篇文章
     * @param id 文章id
     * @return 详细文章dto
     */
    ArticleDto getOneById(Long id);

    /**
     * 删除一篇文章
     * @param id 文章id
     */
    void deleteById(Long id);

    /**
     * 查询所有文章
     * @param page 页码
     * @return 简单文章dto
     */
    List<ArticleSimpleDto> list(Integer page);

}
