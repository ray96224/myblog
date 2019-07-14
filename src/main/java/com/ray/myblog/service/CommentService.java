package com.ray.myblog.service;

import com.github.pagehelper.PageInfo;
import com.ray.myblog.dto.CommentDto;
import com.ray.myblog.entity.CommentInfo;

/**
 * @author: ray
 * @create: 2019/7/11
 */
public interface CommentService {

    /**
     * 添加评论
     * @param commentDto
     */
    void addComment(CommentDto commentDto);

    /**
     * 根据文章查询评论
     * @return
     */
    PageInfo listByArticle(Long articleId, Integer pageNum, Integer pageSize);

    /**
     * 查询所有评论
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getAll(Integer pageNum, Integer pageSize);

    /**
     * 删除一条评论
     * @param commentId
     * @param relationId
     */
    void delete(Long commentId, Long relationId);
}
