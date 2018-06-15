package com.mr.bomkpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Administrator
 */
@Controller
public class OrderController {

    @GetMapping("/order/getOrders")
    public String getOrders(Model model){
        return "/order/list";
    }

}
