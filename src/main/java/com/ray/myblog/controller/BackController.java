package com.ray.myblog.controller;

import com.github.pagehelper.PageInfo;
import com.ray.myblog.dao.ArticleInfoMapper;
import com.ray.myblog.dao.CommentInfoMapper;
import com.ray.myblog.dao.SystemVisitMapper;
import com.ray.myblog.dto.ArticleDto;
import com.ray.myblog.entity.ArticleInfo;
import com.ray.myblog.entity.CommentInfo;
import com.ray.myblog.service.ArticleService;
import com.ray.myblog.service.CommentService;
import com.ray.myblog.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author: ray
 * @create: 2019/7/12
 */

@Controller
public class BackController {

    @Resource
    SystemVisitMapper systemVisitMapper;

    @Resource
    CommentInfoMapper commentInfoMapper;

    @Resource
    ArticleInfoMapper articleInfoMapper;

    @Autowired
    CommentService commentService;

    @Autowired
    SystemLogService systemLogService;

    @Autowired
    ArticleService articleService;


    /**
     * 后台主页
     * @return
     */
    @RequestMapping("/dashboard")
    public String dashboardPage(Model model){
        Long totalVisit = systemVisitMapper.getTimes();
        List<CommentInfo> list = commentInfoMapper.selectByExample(null);
        int comments = list.size();
        List<ArticleInfo> articleInfos = articleInfoMapper.selectByExample(null);
        int articles = articleInfos.size();
        model.addAttribute("totalVisit", totalVisit);
        model.addAttribute("comments", comments);
        model.addAttribute("articles", articles);
        return "back/dashboard";
    }

    /**
     * 评论列表
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/back/list-comments/{pageNum}")
    public PageInfo getComments(@PathVariable Integer pageNum){
        PageInfo pageInfo = commentService.getAll(pageNum, 5);
        return pageInfo;
    }

    /**
     * 日志列表
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/back/list-logs/{pageNum}")
    public PageInfo getLogs(@PathVariable Integer pageNum){
        PageInfo pageInfo = systemLogService.getAll(pageNum, 5);
        return pageInfo;
    }

    /**
     * 文章列表
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/back/list-articles/{pageNum}")
    public PageInfo getArticles(@PathVariable Integer pageNum){
        PageInfo pageInfo = articleService.list(pageNum,10);
        return pageInfo;
    }

    /**
     * 删除评论
     * @param commentId
     * @param relationId
     * @return
     */
    @ResponseBody
    @RequestMapping("/back/delete-comment/{commentId}/{relationId}")
    public String deleteComment(@PathVariable Long commentId,
                                @PathVariable Long relationId){
        commentService.delete(commentId, relationId);
        return "success";
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/back/delete-article/{id}")
    public String deleteArticle(@PathVariable Long id){
        articleService.deleteById(id);
        return "success";
    }

    /**
     * 编辑文章界面
     * @return
     */
    @RequestMapping("/back/edit")
    public String editPage(){
        return "add-article";
    }

    /**
     * 添加文章
     * @param articleDto
     * @param file
     * @return
     */
    @RequestMapping("/back/save-article")
    public String addArticle(ArticleDto articleDto,
                             @RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            System.out.println("文件不存在");
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String filePath = "D://Blog Resources//images//";
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        articleDto.setImageUrl(fileName);
        articleService.addArticle(articleDto);
        return "redirect:/dashboard";
    }

    /**
     * 跳转修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/back/edit-article/{id}")
    public String editArticle(@PathVariable Long id, Model model){
        ArticleDto articleDto = articleService.getOneById(id);
        model.addAttribute("articleDto", articleDto);
        return "back/edit-article";
    }

    /**
     * 保存修改
     * @param articleDto
     * @return
     */
    @RequestMapping("/back/save-article-change")
    public String saveChange(ArticleDto articleDto){
        articleService.updateArticle(articleDto);
        return "redirect:/dashboard";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/back/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request){

        System.out.println(username+":"+password);
        if ("wl5944".equals(username) && "ray75144".equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            return "redirect:/dashboard";
        } else {
            return "redirect:/back/login-page";
        }
    }

    /**
     * 注销登录
     * @param request
     * @return
     */
    @RequestMapping("/back/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/back/login-page";
    }

}
