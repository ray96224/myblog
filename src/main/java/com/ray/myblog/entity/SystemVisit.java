package com.ray.myblog.entity;

/**
 * @author: ray
 * @create: 2019/7/12
 */
public class SystemVisit {
    private int id;
    private Long visitTimes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(Long visitTimes) {
        this.visitTimes = visitTimes;
    }
}
