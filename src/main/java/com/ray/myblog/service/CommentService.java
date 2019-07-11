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
     * 查询评论
     * @return
     */
    PageInfo list(Long articleId, Integer pageNum, Integer pageSize);
}
