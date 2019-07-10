package com.ray.myblog.service;

import com.github.pagehelper.PageInfo;

/**
 * @author: ray
 * @create: 2019/7/10
 */
public interface CategoryService {

    /**
     * 查询所有分类
     * @param pageNum 页码
     * @return
     */
    PageInfo listCategory(Integer pageNum);

    /**
     * 根据分类id查找分类文章关联
     * @param categoryId
     * @return
     */
    PageInfo listArticleCategoryByCategoryId(Long categoryId);
}
