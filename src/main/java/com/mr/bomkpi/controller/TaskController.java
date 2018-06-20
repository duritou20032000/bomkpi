package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.Task;
import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.entity.TaskOrder;
import com.mr.bomkpi.repository.TaskCounterRepository;
import com.mr.bomkpi.repository.TaskOrderRepository;
import com.mr.bomkpi.repository.TaskRepository;
import com.mr.bomkpi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskCounterRepository counterRepository;
    @Autowired
    private TaskOrderRepository orderRepository;


    @GetMapping("/task/getTasks")
    public String getTasks(Model model){
      return "task/list";
    }

    @PostMapping("/task/init")
    public String init(Task task,Principal principal){
      //按照多个柜台来生成子任务
        List<TaskCounter> counters=null;
        TaskOrder order = orderRepository.findByOrderCode(task.getOrderCode());
        String whseCode =  order.getWhseCode();
        String amount = task.getProductCount();
        String[] ns = null;
        if(!StringUtil.isEmptyOrNull(amount)){
            ns = amount.split(",");
        }
       if(!StringUtil.isEmptyOrNull(whseCode)){
           counters = counterRepository.findAllByWhseCode(whseCode);
       }
        for (int i = 0; i < counters.size(); i++) {
            String code = counters.get(i).getCounterCode();
            String name = counters.get(i).getCounterName();
            String mount = ns[i];
            String taskCode = whseCode+"-"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"-"+i;
            String createrCode = principal.getName();
            String productCount=ns[i];
            task.setCounterCode(code);
            task.setCounterName(name);
            task.setCreaterCode(createrCode);
            task.setProductCount(productCount);
            task.setTaskCode(taskCode);
            task.setCreationDate(new Date());
            task.setProductName(order.getProductName());
            task.setProductCode(order.getProductCode());
            task.setTaskStatus(1);
            taskRepository.saveAndFlush(task);
        }
        return "redirect:/task/getTasks";
    }

}
