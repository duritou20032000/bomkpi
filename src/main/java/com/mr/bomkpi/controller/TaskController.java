package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.Task;
import com.mr.bomkpi.repository.TaskCounterRepository;
import com.mr.bomkpi.repository.TaskOrderRepository;
import com.mr.bomkpi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskCounterRepository counterRepository;
    @Autowired
    private TaskOrderRepository orderRepository;


    @GetMapping("/task/getTasks")
    public String getTasks(Model model){

        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks",tasks);
      return "task/list";
    }


}
