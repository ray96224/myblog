package com.ray.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ray.myblog.dao.*;
import com.ray.myblog.dto.ArticleDto;
import com.ray.myblog.dto.ArticleSimpleDto;
import com.ray.myblog.entity.*;
import com.ray.myblog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        ArticleDto articleDto = new ArticleDto();
        //articleInfo
        ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(id);
        articleDto.setId(articleInfo.getId());
        articleDto.setTitle(articleInfo.getTitle());
        articleDto.setSummary(articleInfo.getSummary());
        articleDto.setViewTimes(articleInfo.getViewTimes());
        articleDto.setTop(articleInfo.getIsTop());
        articleDto.setCreated(articleInfo.getCreated());
        //articleContent
        ArticleContentExample articleContentExample = new ArticleContentExample();
        articleContentExample.or().andArticleIdEqualTo(articleInfo.getId());
        List<ArticleContent> articleContents = articleContentMapper.selectByExampleWithBLOBs(articleContentExample);
        ArticleContent articleContent = articleContents.get(0);
        articleDto.setContentId(articleContent.getId());
        articleDto.setContent(articleContent.getContent());
        //ArticleImage
        ArticleImage imageByArticleId = getImageByArticleId(articleInfo.getId());
        articleDto.setImageId(imageByArticleId.getId());
        articleDto.setImageUrl(imageByArticleId.getImageUrl());
        //CategoryInfo
        ArticleCategory articleCategoryByArticleId = getArticleCategoryByArticleId(articleInfo.getId());
        articleDto.setRelationId(articleCategoryByArticleId.getId());
        CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(articleCategoryByArticleId.getCategoryId());
        articleDto.setCategoryId(categoryInfo.getId());
        articleDto.setCategoryName(categoryInfo.getName());

        return articleDto;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public PageInfo list(Integer page, Integer pageSize) {
        List<ArticleSimpleDto> list = new ArrayList<>();
        PageHelper.startPage(page,pageSize);
        List<ArticleInfo> articleInfos = articleInfoMapper.selectByExample(null);
        PageInfo pageInfo = new PageInfo(articleInfos);
        for (int i = 0; i < articleInfos.size(); i++ ){
            ArticleSimpleDto articleSimpleDto = new ArticleSimpleDto();
            //articleInfo
            ArticleInfo articleInfo = articleInfos.get(i);
            articleSimpleDto.setId(articleInfo.getId());
            articleSimpleDto.setTitle(articleInfo.getTitle());
            articleSimpleDto.setTop(articleInfo.getIsTop());
            articleSimpleDto.setCreated(articleInfo.getCreated());
            //articleImage
            ArticleImage imageByArticleId = getImageByArticleId(articleInfo.getId());
            articleSimpleDto.setImageId(imageByArticleId.getId());
            articleSimpleDto.setImageUrl(imageByArticleId.getImageUrl());
            //CategoryInfo
            ArticleCategory articleCategoryByArticleId = getArticleCategoryByArticleId(articleInfo.getId());
            CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(articleCategoryByArticleId.getCategoryId());
            articleSimpleDto.setCategoryId(categoryInfo.getId());
            articleSimpleDto.setCategoryName(categoryInfo.getName());
            list.add(articleSimpleDto);
        }
        pageInfo.setList(list);
        return pageInfo;
    }

    /**
     * 获取图片信息
     * @param articleId
     * @return
     */
    public ArticleImage getImageByArticleId(Long articleId){
        ArticleImageExample articleImageExample = new ArticleImageExample();
        articleImageExample.or().andArticleIdEqualTo(articleId);
        List<ArticleImage> articleImages = articleImageMapper.selectByExample(articleImageExample);
        return articleImages.get(0);
    }

    /**
     * 获取关联信息
     * @param articleId
     * @return
     */
    public ArticleCategory getArticleCategoryByArticleId(Long articleId){
        ArticleCategoryExample articleCategoryExample = new ArticleCategoryExample();
        articleCategoryExample.or().andArticleIdEqualTo(articleId);
        List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(articleCategoryExample);
        return  articleCategories.get(0);
    }
}
