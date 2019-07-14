package com.ray.myblog.service;

import com.github.pagehelper.PageInfo;
import com.ray.myblog.entity.SystemLog;

/**
 * @author: ray
 * @create: 2019/7/12
 */
public interface SystemLogService {

    /**
     * 插入访问日志
     * @param systemLog
     */
    void insertSysLog(SystemLog systemLog);

    /**
     * 获取日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getAll(Integer pageNum, Integer pageSize);
}
