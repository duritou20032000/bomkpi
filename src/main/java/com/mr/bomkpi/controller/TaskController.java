package com.mr.bomkpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
    @GetMapping("/task/getTasks")
    public String getTasks(Model model){
      return "/task/list";
    }
}
