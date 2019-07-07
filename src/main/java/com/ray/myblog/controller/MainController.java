package com.ray.myblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: ray
 * @create: 2019/7/7
 */
@Controller
public class MainController {


    @RequestMapping("/hello")
    public String hello(Model model){
        return "hello";
    }
}
