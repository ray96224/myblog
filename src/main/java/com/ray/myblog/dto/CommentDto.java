package com.ray.myblog.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author: ray
 * @create: 2019/7/11
 */
public class CommentDto {
    //commentInfo
    private Long id;

    @NotNull
    private String content;

    @Pattern(regexp = "/^[a-zA-Z]{1,20}|[\\u4e00-\\u9fa5]{1,10}$/")
    private String name;

    @Pattern(regexp = "/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/")
    private String email;
    private String ip;
    private Boolean isEffective;
    private Date created;

    //articleInfo
    private Long articleId;
    private String articleTitle;

    //articleComment
    private Long relationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Boolean getEffective() {
        return isEffective;
    }

    public void setEffective(Boolean effective) {
        isEffective = effective;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }
}
