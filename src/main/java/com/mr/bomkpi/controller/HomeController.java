package com.mr.bomkpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    /**
     * 欢迎页
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 登录页
     *
     * @return
     */
    @GetMapping("/login")
    public String loginPage() {
        return  "login";
    }

    @PostMapping("/login")
    public String login(){
        System.out.println("-------------------->>>>>>>>>>>>>>>>>>>>>>POST LOGIN>>>>>>>>>>>>>");
        return "redirect:index";
    }
}
