package com.ray.myblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ray.myblog.dao.ArticleCommentMapper;
import com.ray.myblog.dao.CommentInfoMapper;
import com.ray.myblog.dto.CommentDto;
import com.ray.myblog.entity.ArticleComment;
import com.ray.myblog.entity.ArticleCommentExample;
import com.ray.myblog.entity.CommentInfo;
import com.ray.myblog.service.CommentService;
import org.springframework.stereotype.Service;

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

    @Override
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
    public PageInfo list(Long articleId, Integer pageNum, Integer pageSize) {
        List<CommentInfo> list = new ArrayList<>();
        ArticleCommentExample example = new ArticleCommentExample();
        example.or().andArticleIdEqualTo(articleId);
        PageHelper.startPage(pageNum,pageSize);
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
}
