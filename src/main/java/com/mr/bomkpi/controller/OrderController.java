package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.TaskOrder;
import com.mr.bomkpi.repository.TaskOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Administrator
 */
@Controller
public class OrderController {
    @Autowired
    private TaskOrderRepository orderRepository;

    @GetMapping("/order/getOrders")
    public String getOrders(Model model) {
        // 需要重新探讨所需的业务
        List<TaskOrder> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @GetMapping("/order/form")
    public String form(Model model, Long id) {
        TaskOrder order = orderRepository.getOne(id);
        model.addAttribute("order", order);
        return "order/form";
    }


}
