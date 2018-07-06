package com.mr.bomkpi.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 负责页面在thymeleaf框架下跳转
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/counter")
    public String counterList() {
        return "/counter/list";
    }


    @PostMapping("/user/login")
    public String login(String username) {
        System.out.println(".......>>>>>>>>"+username);
        return "welcome";
    }

    @GetMapping("/user/logout")
    public String logout() {
        return "login";
    }



    @GetMapping("/user/profile")
    public List<User> profile(){
        //当前登录的用户
        return null;
    }

}
