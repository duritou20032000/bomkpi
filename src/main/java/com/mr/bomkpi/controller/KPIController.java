package com.mr.bomkpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KPIController {
    @GetMapping("/kpi/getKpis")
    public String getKpis(Model model){
        return "/kpi/list";
    }
}
