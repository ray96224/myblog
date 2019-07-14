package com.ray.myblog.dao;

import com.ray.myblog.entity.SystemLog;
import com.ray.myblog.entity.SystemLogExample;
import java.util.List;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    List<SystemLog> selectByExample(SystemLogExample example);

    SystemLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
}