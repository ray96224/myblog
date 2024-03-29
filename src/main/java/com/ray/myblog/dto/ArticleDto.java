package com.ray.myblog.dto;

import java.util.Date;

/**
 * @author: ray
 * @create: 2019/7/7
 */
public class ArticleDto {

    //基本 ArticleInfo
    private Long id;
    private String title;
    private String summary;
    private Integer viewTimes;
    private Boolean isTop;
    private Date created;

    //内容 ArticleContent
    private Long contentId;
    private String content;

    //图片 ArticleImage
    private Long imageId;
    private String imageUrl;

    //分类 CategoryInfo
    private Long categoryId;
    private String categoryName;

    //关联 ArticleCategory
    private Long relationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(Integer viewTimes) {
        this.viewTimes = viewTimes;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", viewTimes=" + viewTimes +
                ", isTop=" + isTop +
                ", created=" + created +
                ", contentId=" + contentId +
                ", content='" + content + '\'' +
                ", imageId=" + imageId +
                ", imageUrl='" + imageUrl + '\'' +
                ", categoryId=" + categoryId +
                ", name='" + categoryName + '\'' +
                ", relationId=" + relationId +
                '}';
    }
}
