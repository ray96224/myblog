package com.ray.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ray.myblog.dao.*;
import com.ray.myblog.dto.ArticleDto;
import com.ray.myblog.dto.ArticleSimpleDto;
import com.ray.myblog.entity.*;
import com.ray.myblog.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    ArticleCommentMapper articleCommentMapper;

    @Resource
    CommentInfoMapper commentInfoMapper;


    @Override
    @Transactional
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
    @Transactional
    public void updateArticle(ArticleDto articleDto) {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(articleDto.getId());
        articleInfo.setTitle(articleDto.getTitle());
        articleInfo.setSummary(articleDto.getSummary());
        articleInfo.setIsTop(articleDto.getTop());
        articleInfoMapper.updateByPrimaryKeySelective(articleInfo);

        ArticleContent articleContent = new ArticleContent();
        articleContent.setId(articleDto.getContentId());
        articleContent.setContent(articleDto.getContent());
        articleContentMapper.updateByPrimaryKeySelective(articleContent);

        CategoryInfo info = categoryInfoMapper.selectByPrimaryKey(articleDto.getCategoryId());
        if (!info.getName().equals(articleDto.getCategoryName())){

            articleCategoryMapper.deleteByPrimaryKey(articleDto.getRelationId());

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
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        //内容id
        ArticleContentExample example = new ArticleContentExample();
        example.or().andArticleIdEqualTo(id);
        List<ArticleContent> articleContents = articleContentMapper.selectByExample(example);
        Long contentId = articleContents.get(0).getId();
        //图片id
        ArticleImageExample example1 = new ArticleImageExample();
        example1.or().andArticleIdEqualTo(id);
        List<ArticleImage> articleImages = articleImageMapper.selectByExample(example1);
        Long imageId = articleImages.get(0).getId();
        //分类关联id
        ArticleCategoryExample example2 = new ArticleCategoryExample();
        example2.or().andArticleIdEqualTo(id);
        List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(example2);
        Long relationId = articleCategories.get(0).getId();
        //删除对应评论
        ArticleCommentExample example3 = new ArticleCommentExample();
        example3.or().andArticleIdEqualTo(id);
        List<ArticleComment> articleComments = articleCommentMapper.selectByExample(example3);
        for (int i = 0; i <articleComments.size(); i++){
            commentInfoMapper.deleteByPrimaryKey(articleComments.get(i).getCommentId());
            articleCommentMapper.deleteByPrimaryKey(articleComments.get(i).getId());
        }

        articleInfoMapper.deleteByPrimaryKey(id);
        articleContentMapper.deleteByPrimaryKey(contentId);
        articleImageMapper.deleteByPrimaryKey(imageId);
        articleCategoryMapper.deleteByPrimaryKey(relationId);
    }

    @Override
    public PageInfo list(Integer page, Integer pageSize) {
        List<ArticleSimpleDto> list = new ArrayList<>();
        String order = "id DESC";
        PageHelper.startPage(page,pageSize,order);
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

    @Override
    public PageInfo listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        List<ArticleSimpleDto> list = new ArrayList<>();
        ArticleCategoryExample example = new ArticleCategoryExample();
        example.or().andCategoryIdEqualTo(categoryId);
        String order = "id DESC";
        PageHelper.startPage(pageNum, pageSize, order);
        List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(articleCategories);
        for (int i = 0; i < articleCategories.size(); i++){
            ArticleSimpleDto articleSimpleDto = new ArticleSimpleDto();
            //articleInfo
            ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(articleCategories.get(i).getArticleId());
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
     * 增加查看次数
     * @param articleInfo
     */
    @Override
    public void increaseViewTimes(ArticleInfo articleInfo) {
        articleInfoMapper.updateByPrimaryKeySelective(articleInfo);
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
