package com.ray.myblog;

import com.ray.myblog.dao.SystemVisitMapper;
import com.ray.myblog.service.ArticleService;
import com.ray.myblog.service.CategoryService;
import com.ray.myblog.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CommentService commentService;

    @Resource
    SystemVisitMapper systemVisitMapper;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testQuery(){
//        PageInfo list = commentService.list(1L, 1, 5);
//        for (int i = 0;i < list.getList().size(); i++){
//            String content = ((CommentInfo) list.getList().get(i)).getContent();
//            System.out.println(content);
//        }
        systemVisitMapper.increaseTimes();
        Long times = systemVisitMapper.getTimes();
        System.out.println(times);

    }
}
