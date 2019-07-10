package com.ray.myblog.controller;

import com.github.pagehelper.PageInfo;
import com.ray.myblog.dto.ArticleDto;
import com.ray.myblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/")
    public String indexPage(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model){
        PageInfo pageInfo = articleService.list(pageNum,5);
        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }

    /**
     * 归档页
     * @return
     */
    @RequestMapping("/list")
    public String listPage(){
        return "pages/list";
    }


    @RequestMapping("/article/{id}")
    public String articlePage(@PathVariable Long id, Model model){
        ArticleDto articleDto = articleService.getOneById(id);
        model.addAttribute("article", articleDto);
        return "pages/article";
    }

//    @ResponseBody
//    @RequestMapping("/test/{id}")
//    public ArticleDto articlePageTest(@PathVariable Long id){
//        ArticleDto articleDto = articleService.getOneById(id);
//        return articleDto;
//    }

    /**
     * 归档页面ajax请求
     * @param pageNum
     * @return
     */
    @ResponseBody
    @GetMapping("/list-title/{pageNum}")
    public PageInfo listTile(@PathVariable("pageNum") Integer pageNum){
        PageInfo pageInfo = articleService.list(pageNum, 13);
        return pageInfo;
    }
}
