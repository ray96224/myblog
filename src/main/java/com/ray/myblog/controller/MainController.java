package com.ray.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: ray
 * @create: 2019/7/7
 */
@Controller
public class MainController {

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("hi","Hello Ray");
        return "hello";
    }
}
