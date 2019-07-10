package com.ray.myblog.service;

import com.github.pagehelper.PageInfo;
import com.ray.myblog.dto.ArticleDto;

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
     * 查询一篇文章详细版ArticleDto
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
     * 查询所有文章简单版ArticleSimpleDto
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 简单文章dto的封装PageInfo对象
     */
    PageInfo list(Integer pageNum, Integer pageSize);

    /**
     * 根据分类id查询文章简单版ArticleSimpleDto
     * @param categoryId
     * @return
     */
    PageInfo listByCategory(Long categoryId,Integer pageNum, Integer pageSize);

}
