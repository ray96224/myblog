package com.ray.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.ray.myblog.dao.*;
import com.ray.myblog.dto.ArticleDto;
import com.ray.myblog.dto.ArticleSimpleDto;
import com.ray.myblog.entity.*;
import com.ray.myblog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ray
 * @create: 2019/7/7
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleInfoMapper articleInfoMapper;

    @Resource
    ArticleContentMapper articleContentMapper;

    @Resource
    ArticleImageMapper articleImageMapper;

    @Resource
    CategoryInfoMapper categoryInfoMapper;

    @Resource
    ArticleCategoryMapper articleCategoryMapper;


    @Override
    public void addArticle(ArticleDto articleDto) {
        //插入ArticleInfo
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle(articleDto.getTitle());
        articleInfo.setSummary(articleDto.getSummary());
        articleInfo.setIsTop(articleDto.getTop());
        articleInfoMapper.insertSelective(articleInfo);

        //插入ArticleContent
        ArticleContent articleContent = new ArticleContent();
        articleContent.setArticleId(articleInfo.getId());
        articleContent.setContent(articleDto.getContent());
        articleContentMapper.insertSelective(articleContent);

        //插入ArticleImage
        ArticleImage articleImage = new ArticleImage();
        articleImage.setArticleId(articleInfo.getId());
        articleImage.setImageUrl(articleDto.getImageUrl());
        articleImageMapper.insertSelective(articleImage);

        //插入CategoryInfo
        CategoryInfo categoryInfo = new CategoryInfo();
        CategoryInfoExample categoryInfoExample = new CategoryInfoExample();
        categoryInfoExample.or().andNameEqualTo(articleDto.getCategoryName());
        List<CategoryInfo> categoryInfos = categoryInfoMapper.selectByExample(categoryInfoExample);
        if (categoryInfos.size() == 0){
            categoryInfo.setName(articleDto.getCategoryName());
            categoryInfo.setIsEffective(true);
            categoryInfoMapper.insertSelective(categoryInfo);
        }else {
            categoryInfo = categoryInfos.get(0);
        }

        //插入ArticleCategory
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setArticleId(articleInfo.getId());
        articleCategory.setCategoryId(categoryInfo.getId());
        articleCategoryMapper.insertSelective(articleCategory);


    }

    @Override
    public ArticleDto getOneById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<ArticleSimpleDto> list(Integer page) {
        List<ArticleSimpleDto> list = new ArrayList<>();
        PageHelper.startPage(page,5);
        List<ArticleInfo> articleInfos = articleInfoMapper.selectByExample(null);
        for (int i = 0; i < articleInfos.size(); i++ ){
            ArticleSimpleDto articleSimpleDto = new ArticleSimpleDto();
            ArticleInfo articleInfo = articleInfos.get(i);
            articleSimpleDto.setId(articleInfo.getId());
            articleSimpleDto.setTitle(articleInfo.getTitle());
            articleSimpleDto.setTop(articleInfo.getIsTop());
            articleSimpleDto.setCreated(articleInfo.getCreated());

            ArticleImageExample articleImageExample = new ArticleImageExample();
            articleImageExample.or().andArticleIdEqualTo(articleInfo.getId());
            List<ArticleImage> articleImages = articleImageMapper.selectByExample(articleImageExample);
            articleSimpleDto.setImageId(articleImages.get(0).getId());
            articleSimpleDto.setImageUrl(articleImages.get(0).getImageUrl());

            ArticleCategoryExample articleCategoryExample = new ArticleCategoryExample();
            articleCategoryExample.or().andArticleIdEqualTo(articleInfo.getId());
            List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(articleCategoryExample);
            CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(articleCategories.get(0).getCategoryId());
            articleSimpleDto.setCategoryId(categoryInfo.getId());
            articleSimpleDto.setCategoryName(categoryInfo.getName());
            list.add(articleSimpleDto);
        }
        return list;
    }
}
