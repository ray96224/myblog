package com.ray.myblog;

import com.ray.myblog.dto.ArticleDto;
import com.ray.myblog.dto.ArticleSimpleDto;
import com.ray.myblog.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.RandomAccess;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {

    @Autowired
    ArticleService articleService;

    @Test
    public void contextLoads() {
        for (int i = 7; i < 30; i++){
            ArticleDto articleDto = new ArticleDto();
            articleDto.setTitle("我是第"+i+"篇文章");
            articleDto.setSummary("概要");
            articleDto.setTop(false);
            articleDto.setContent("文章内容，反正就是很长很长很长很长很长很长很长很长很长");
            articleDto.setImageUrl("c://假装是URL");
            articleDto.setCategoryName("第"+ (i/3 + 2) +"个分类");
            articleService.addArticle(articleDto);
        }

    }

    @Test
    public void testQuery(){
        List<ArticleSimpleDto> list = articleService.list(2);
        for (ArticleSimpleDto dto : list){
            System.out.println(dto);
        }
    }

}
