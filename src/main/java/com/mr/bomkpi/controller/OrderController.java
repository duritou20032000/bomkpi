package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.entity.TaskOrder;
import com.mr.bomkpi.repository.TaskCounterRepository;
import com.mr.bomkpi.repository.TaskOrderRepository;
import com.mr.bomkpi.repository.TaskRepository;
import com.mr.bomkpi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class OrderController {

    @Autowired
    private TaskCounterRepository counterRepository;
    @Autowired
    private TaskOrderRepository orderRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/getOrders")
    public String getOrders(Model model) {
        // 需要重新探讨所需的业务
        List<TaskOrder> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order/list";
    }

    /**
     *
     * @param model
     * @param id
     * @return
     */
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
        return "order/form";
    }

    /**
     * 应该是将order 数据带过来，然后通过order 将柜台的子任务数据量带上，数据落库后，需要分配的次数和分配数量字段。
     * @param model
     * @param order
     * @return
     */
    @PostMapping("/order/generateTask")
    public String generateTask(Model model, TaskOrder order, Principal principal){
        orderService.saveTasks(order,principal);
        return "task/list";
    }


}
