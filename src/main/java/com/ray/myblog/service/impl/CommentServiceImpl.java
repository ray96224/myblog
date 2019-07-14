package com.ray.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ray.myblog.dao.ArticleCommentMapper;
import com.ray.myblog.dao.ArticleInfoMapper;
import com.ray.myblog.dao.CommentInfoMapper;
import com.ray.myblog.dto.CommentDto;
import com.ray.myblog.entity.ArticleComment;
import com.ray.myblog.entity.ArticleCommentExample;
import com.ray.myblog.entity.ArticleInfo;
import com.ray.myblog.entity.CommentInfo;
import com.ray.myblog.service.ArticleService;
import com.ray.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ray
 * @create: 2019/7/11
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentInfoMapper commentInfoMapper;

    @Resource
    ArticleCommentMapper articleCommentMapper;

    @Resource
    ArticleInfoMapper articleInfoMapper;

    @Override
    @Transactional
    public void addComment(CommentDto commentDto) {
        CommentInfo commentInfo = new CommentInfo();
        commentInfo.setContent(commentDto.getContent());
        commentInfo.setName(commentDto.getName());
        commentInfo.setEmail(commentDto.getEmail());
        commentInfo.setIp(commentDto.getIp());
        commentInfoMapper.insertSelective(commentInfo);

        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticleId(commentDto.getArticleId());
        articleComment.setCommentId(commentInfo.getId());
        articleCommentMapper.insertSelective(articleComment);
    }

    @Override
    public PageInfo listByArticle(Long articleId, Integer pageNum, Integer pageSize) {
        List<CommentInfo> list = new ArrayList<>();
        ArticleCommentExample example = new ArticleCommentExample();
        example.or().andArticleIdEqualTo(articleId);
        String order = "id DESC";
        PageHelper.startPage(pageNum,pageSize,order);
        List<ArticleComment> articleComments = articleCommentMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(articleComments);
        for (int i = 0; i < articleComments.size(); i++){
            ArticleComment articleComment = articleComments.get(i);
            CommentInfo commentInfo = commentInfoMapper.selectByPrimaryKey(articleComment.getCommentId());
            list.add(commentInfo);
        }
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public PageInfo getAll(Integer pageNum, Integer pageSize) {
        List<CommentDto> commentDtos = new ArrayList<>();
        String order = "id DESC";
        PageHelper.startPage(pageNum, pageSize, order);
        List<CommentInfo> list = commentInfoMapper.selectByExample(null);
        PageInfo pageInfo = new PageInfo(list);
        for (int i = 0; i < list.size(); i++){
            CommentDto commentDto = new CommentDto();
            //commentInfo
            commentDto.setId(list.get(i).getId());
            commentDto.setIp(list.get(i).getIp());
            commentDto.setName(list.get(i).getName());
            commentDto.setEmail(list.get(i).getEmail());
            commentDto.setContent(list.get(i).getContent());
            commentDto.setCreated(list.get(i).getCreated());
            commentDto.setEffective(list.get(i).getIsEffective());
            //articleComment
            Long commentId = list.get(i).getId();
            ArticleCommentExample example = new ArticleCommentExample();
            example.or().andCommentIdEqualTo(commentId);
            List<ArticleComment> articleComments = articleCommentMapper.selectByExample(example);
            ArticleComment articleComment = articleComments.get(0);
            commentDto.setRelationId(articleComment.getId());
            //articleInfo
            ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(articleComment.getArticleId());
            commentDto.setArticleId(articleInfo.getId());
            commentDto.setArticleTitle(articleInfo.getTitle());
            commentDtos.add(commentDto);
        }
        pageInfo.setList(commentDtos);
        return pageInfo;
    }

    @Override
    @Transactional
    public void delete(Long commentId, Long relationId) {
        commentInfoMapper.deleteByPrimaryKey(commentId);
        articleCommentMapper.deleteByPrimaryKey(relationId);
    }
}
