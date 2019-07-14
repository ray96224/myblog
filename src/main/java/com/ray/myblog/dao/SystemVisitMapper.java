package com.ray.myblog.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author: ray
 * @create: 2019/7/12
 */
public interface SystemVisitMapper {

    /**
     * 查询博客总访问次数
     * @return
     */
    @Select("select visit_times from tbl_sys_visit where id = 1")
    Long getTimes();

    /**
     * 更新查询次数
     */
    @Update("update tbl_sys_visit set visit_times = visit_times+1")
    void increaseTimes();
}
