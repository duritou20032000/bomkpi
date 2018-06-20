package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.Task;
import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.repository.TaskCounterRepository;
import com.mr.bomkpi.repository.TaskRepository;
import com.mr.bomkpi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskCounterRepository counterRepository;


    @GetMapping("/task/getTasks")
    public String getTasks(Model model){
      return "task/list";
    }

    @PostMapping("/task/init")
    public String init(Model model,Task task){
      //按照多个柜台来生成子任务
        List<TaskCounter> list=null;
       String whseCode =  task.getTaskOrder().getWhseCode();
       if(!StringUtil.isEmptyOrNull(whseCode)){
           list = counterRepository.findAllByWhseCode(whseCode);
       }



        System.out.println("test");
//        taskRepository.save(task);
        return "order/form";
    }

//    子任务编码生成规则
    protected  String  generateTasks(Task task, Principal principal){
//        仓库加时间戳


        return null;
    }
}
