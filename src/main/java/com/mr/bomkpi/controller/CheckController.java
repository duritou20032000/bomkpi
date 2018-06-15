package com.mr.bomkpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckController {

    @GetMapping("/check/getChecks")
    public String getChecks(Model model){
        return "/check/list";
    }

}
