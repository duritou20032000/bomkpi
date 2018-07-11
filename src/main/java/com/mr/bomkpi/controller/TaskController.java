package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.Task;
import com.mr.bomkpi.entity.TaskOrder;
import com.mr.bomkpi.entity.User;
import com.mr.bomkpi.entity.UserWhseVo;
import com.mr.bomkpi.repository.*;
import com.mr.bomkpi.service.TaskService;
import com.mr.bomkpi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TaskController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserWhseVoRepository userWhseVoRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskCounterRepository counterRepository;
    @Autowired
    private TaskOrderRepository orderRepository;
    @Autowired
    private TaskService taskService;

    /**
     * 该用户所有的任务
     * @param order
     * @param principal
     * @param fuzzy
     * @param fuzzySearch
     * @return
     */
    @PostMapping("/task/getTasks")
    public Map<String,List<Task>> getTasks(Task task, Principal principal, String fuzzy, String fuzzySearch){
        String username = principal.getName();
        Map<String, List<Task>> map = new HashMap<>();
        List<Task> tasks = new ArrayList<>();
        List<UserWhseVo> userWhses = new ArrayList<>();
        if (!StringUtil.isEmptyOrNull(username)) {
            User user = userRepository.findByUsername(username);
            userWhses = userWhseVoRepository.findAllByUserId(user.getUserId());
        }
        tasks = taskService.queryListOnCondition(task,userWhses,fuzzy,fuzzySearch);
        map.put("data",tasks);
        return map;

    }


}
