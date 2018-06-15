package com.mr.bomkpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamTaskController {

    @GetMapping("/getTeamTasks")
    public String getTeamTasks(Model model){
        return "/team/list";
    }

}
