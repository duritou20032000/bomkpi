package com.mr.bomkpi.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


    @GetMapping("/user/login")
    public String login(String username) {
        return "welcome";
    }

    @GetMapping("/user/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/badCredentials")
    public String error(Model model) {
        model.addAttribute("msg","用户名或密码错误！");
        return "login";
    }

    @GetMapping("/user/profile")
    public List<User> profile(){
        //当前登录的用户
        return null;
    }

    @GetMapping("/counter")
    public String counterList() {
        return "counter/list";
    }

    @GetMapping("/order")
    public String orderList() {
        return "order/list";
    }
    @GetMapping("/task")
    public String taskList() {
        return "task/list";
    }
    @GetMapping("/task/person")
    public String taskPersonList() {
        return "single/list";
    }
    @GetMapping("/task/team")
    public String taskTeamList() {
        return "team/list";
    }
    @GetMapping("/kpi")
    public String kpiList() {
        return "kpi/list";
    }
    @GetMapping("/kpi/person")
    public String kpiPersonList() {
        return "user/list";
    }

}
