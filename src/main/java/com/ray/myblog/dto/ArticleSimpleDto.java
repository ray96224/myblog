package com.ray.myblog.dto;

import java.util.Date;

/**
 * @author: ray
 * @create: 2019/7/7
 */
public class ArticleSimpleDto {

    private Long id;
    private String title;
    private Boolean isTop;
    private Date created;

    private Long imageId;
    private String imageUrl;

    private Long categoryId;
    private String categoryName;

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

    @Override
    public String toString() {
        return "ArticleSimpleDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isTop=" + isTop +
                ", created=" + created +
                ", imageId=" + imageId +
                ", imageUrl='" + imageUrl + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
