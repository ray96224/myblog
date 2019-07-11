package com.ray.myblog.dao;

import com.ray.myblog.entity.CommentInfo;
import com.ray.myblog.entity.CommentInfoExample;
import java.util.List;

public interface CommentInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommentInfo record);

    int insertSelective(CommentInfo record);

    List<CommentInfo> selectByExample(CommentInfoExample example);

    CommentInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentInfo record);

    int updateByPrimaryKey(CommentInfo record);
}