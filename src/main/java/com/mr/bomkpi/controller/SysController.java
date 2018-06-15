package com.mr.bomkpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SysController {

    @GetMapping("/getSystems")
    public String getSystems(Model model){
        return "/sys/list";
    }

}
