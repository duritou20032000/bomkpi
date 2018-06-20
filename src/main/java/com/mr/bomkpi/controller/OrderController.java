package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.entity.TaskOrder;
import com.mr.bomkpi.repository.TaskCounterRepository;
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
    private TaskCounterRepository counterRepository;
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
        //获取本仓库的柜台为分配任务做准备
        String whseCode = order.getWhseCode();
        List<TaskCounter> counters = counterRepository.findAllByWhseCode(whseCode);
        if(counters.size()==0){
            model.addAttribute("msg","请先为本仓库添加柜台，然后再分配任务！");
        }else{
            model.addAttribute("counters",counters);
        }
        model.addAttribute("order", order);
        return "task/form";
    }


}
