package com.ray.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ray.myblog.dao.ArticleCategoryMapper;
import com.ray.myblog.dao.CategoryInfoMapper;
import com.ray.myblog.entity.ArticleCategory;
import com.ray.myblog.entity.ArticleCategoryExample;
import com.ray.myblog.entity.CategoryInfo;
import com.ray.myblog.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ray
 * @create: 2019/7/10
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryInfoMapper categoryInfoMapper;

    @Resource
    ArticleCategoryMapper articleCategoryMapper;

    @Override
    public PageInfo listCategory(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<CategoryInfo> list = categoryInfoMapper.selectByExample(null);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo listArticleCategoryByCategoryId(Long categoryId) {
        ArticleCategoryExample example = new ArticleCategoryExample();
        example.or().andCategoryIdEqualTo(categoryId);
        List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(articleCategories);
        return pageInfo;
    }
}
