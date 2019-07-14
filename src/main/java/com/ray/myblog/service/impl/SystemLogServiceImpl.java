package com.ray.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ray.myblog.dao.SystemLogMapper;
import com.ray.myblog.entity.SystemLog;
import com.ray.myblog.service.SystemLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ray
 * @create: 2019/7/12
 */

@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Resource
    SystemLogMapper systemLogMapper;

    @Override
    public void insertSysLog(SystemLog systemLog) {
        systemLogMapper.insertSelective(systemLog);
    }

    @Override
    public PageInfo getAll(Integer pageNum, Integer pageSize) {
        String order = "id DESC";
        PageHelper.startPage(pageNum, pageSize, order);
        List<SystemLog> list = systemLogMapper.selectByExample(null);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
