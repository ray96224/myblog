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
    private Integer view_times;
    private Boolean is_top;
    private Date created;

    //内容 ArticleContent
    private Long contentId;
    private String content;

    //图片 ArticleImage
    private Long imageId;
    private String image_url;

    //分类 CategoryInfo
    private Long categoryId;
    private String name;
    private Boolean is_effective;

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

    public Integer getView_times() {
        return view_times;
    }

    public void setView_times(Integer view_times) {
        this.view_times = view_times;
    }

    public Boolean getIs_top() {
        return is_top;
    }

    public void setIs_top(Boolean is_top) {
        this.is_top = is_top;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(Boolean is_effective) {
        this.is_effective = is_effective;
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
                ", view_times=" + view_times +
                ", is_top=" + is_top +
                ", created=" + created +
                ", contentId=" + contentId +
                ", content='" + content + '\'' +
                ", imageId=" + imageId +
                ", image_url='" + image_url + '\'' +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", is_effective=" + is_effective +
                ", relationId=" + relationId +
                '}';
    }
}
