package com.mr.bomkpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String welcome() {
        return "welcome";
    }

    /**
     * 登录页
     *
     * @return
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/user/login")
    public String login() {
        return "welcome";
    }

    @GetMapping("/user/logout")
    public String logout() {
        return "login";
    }

}
