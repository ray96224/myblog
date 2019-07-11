package com.ray.myblog.controller;

import com.github.pagehelper.PageInfo;
import com.ray.myblog.dto.ArticleDto;
import com.ray.myblog.dto.CommentDto;
import com.ray.myblog.service.ArticleService;
import com.ray.myblog.service.CategoryService;
import com.ray.myblog.service.CommentService;
import com.ray.myblog.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 前端
 *
 * @author: ray
 * @create: 2019/7/7
 */
@Controller
public class MainController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CommentService commentService;
    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/")
    public String indexPage(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                            Model model){
        PageInfo pageInfo = articleService.list(pageNum,5);
        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }

    /**
     * 文章页
     * @param id 文章id
     * @param model
     * @return
     */
    @RequestMapping("/article/{id}")
    public String articlePage(@PathVariable Long id, Model model){
        ArticleDto articleDto = articleService.getOneById(id);
        model.addAttribute("article", articleDto);
        return "pages/article";
    }

    /**
     * 归档页面ajax请求
     * @param pageNum 页码
     * @return
     */
    @ResponseBody
    @GetMapping("/list-title/{pageNum}")
    public PageInfo listTile(@PathVariable("pageNum") Integer pageNum){
        PageInfo pageInfo = articleService.list(pageNum, 13);
        return pageInfo;
    }

    /**
     * 分类文章列表页面ajax请求
     * @param categoryId
     * @param pageNum
     * @return
     */
    @ResponseBody
    @GetMapping("/list-title-by-category/{categoryId}/{pageNum}")
    public PageInfo listTitleByCategory(@PathVariable("categoryId") Long categoryId,
                                    @PathVariable("pageNum") Integer pageNum){
        PageInfo pageInfo = articleService.listByCategory(categoryId, pageNum, 10);
        return pageInfo;
    }

    /**
     * 分类页Ajax请求
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/list-category/{pageNum}")
    public PageInfo listCategory(@PathVariable("pageNum") Integer pageNum){
        PageInfo pageInfo = categoryService.listCategory(pageNum);
        return pageInfo;
    }

    /**
     * 显示文章底部评论
     * @param articleId
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/list-comment/{articleId}/{pageNum}")
    public PageInfo listComment(@PathVariable("articleId") Long articleId,
                                @PathVariable("pageNum") Integer pageNum){
        PageInfo pageInfo = commentService.list(articleId, pageNum, 5);
        return pageInfo;
    }


    @RequestMapping("/add-comment/{pageNum}")
    public String addComment(CommentDto commentDto,
                             @PathVariable("pageNum") Integer pageNum,
                             HttpServletRequest request){
        String ipAddress = IpUtil.getIpAddr(request);
        commentDto.setIp(ipAddress);
        commentService.addComment(commentDto);

        Long articleId = commentDto.getArticleId();
        return "forward:/list-comment/"+articleId+"/"+pageNum;
    }
}
