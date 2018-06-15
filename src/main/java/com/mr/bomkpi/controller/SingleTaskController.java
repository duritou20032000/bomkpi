package com.mr.bomkpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SingleTaskController {

    @GetMapping("/singleTask/getSingleTasks")
    public String getSingleTasks(Model model){
        return "/single/list";
    }

}
