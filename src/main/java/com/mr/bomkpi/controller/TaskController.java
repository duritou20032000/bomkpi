package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.Task;
import com.mr.bomkpi.entity.User;
import com.mr.bomkpi.entity.UserWhseVo;
import com.mr.bomkpi.repository.*;
import com.mr.bomkpi.service.TaskService;
import com.mr.bomkpi.support.SimpleResponse;
import com.mr.bomkpi.util.PasswordUtil;
import com.mr.bomkpi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;

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
     *
     * @param principal
     * @param fuzzy
     * @param fuzzySearch
     * @return
     */
    @PostMapping("/task/getTasks")
    public Map<String, List<Task>> getTasks(Task task, Principal principal, String fuzzy, String fuzzySearch) {
        String username = principal.getName();
        Map<String, List<Task>> map = new HashMap<>();
        List<Task> tasks = new ArrayList<>();
        List<UserWhseVo> userWhses = new ArrayList<>();
        if (!StringUtil.isEmptyOrNull(username)) {
            User user = userRepository.findByUsername(username);
            userWhses = userWhseVoRepository.findAllByUserId(user.getUserId());
        }
        tasks = taskService.queryListOnCondition(task, userWhses, fuzzy, fuzzySearch, null);
        map.put("data", tasks);
        return map;
    }

    /**
     * 单人任务查询，单人所属仓库，显示领取后的任务
     *
     * @param task
     * @param principal
     * @param fuzzy
     * @param fuzzySearch
     * @return
     */
    @PostMapping("/task/single/getTasks")
    public Map<String, List<Task>> getSingleTasks(Task task, Principal principal, String fuzzy, String fuzzySearch) {
        String username = principal.getName();
        Map<String, List<Task>> map = new HashMap<>();
        List<Task> tasks = new ArrayList<>();
        List<UserWhseVo> userWhses = new ArrayList<>();
        if (!StringUtil.isEmptyOrNull(username)) {
            User user = userRepository.findByUsername(username);
            userWhses = userWhseVoRepository.findAllByUserId(user.getUserId());
        }
        tasks = taskService.queryListOnCondition(task, userWhses, fuzzy, fuzzySearch, "single");
        map.put("data", tasks);
        return map;
    }

    /**
     * 单人任务绑定
     * 状态 1 未绑定  2 已绑定 3 任务进行中 4任务已结束
     * 返回值 ：0 任务不存在  1 绑定成功 2 任务已被绑定 3 有任务未完成不可以领取新任务
     */
    @PostMapping("/task/singleTaskBind")
    public Map<String, Object> singleTaskBind(String taskCode, String username) {
//        通过任务号获取任务，将任务保存
        Map<String, Object> map = new HashMap<>();
        List<Task> taskBinded = taskRepository.findAllByRequesterAndTaskStatusLessThan(username, 4);
        if (taskBinded.size() > 0) {
            map.put("data", 3);
        } else {
            Task task = taskRepository.findByTaskCode(taskCode);
            if (task != null && username != null) {
                String user = task.getRequester();
                if (StringUtil.isEmptyOrNull(user)) {
                    task.setRequester(username);
                    task.setRequesterDate(new Date());
                    task.setTaskStatus(3);
                    taskRepository.save(task);
                    map.put("data", 1);
                } else {
                    map.put("data", 2);
                }
            } else if (task == null) {
                map.put("data", 0);
            }
        }
        return map;
    }

    @PostMapping("/task/team/login")
    public Map<String, Object> teamLogin(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","该用户不存在！");

        List teamUsers = new ArrayList();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            boolean flag = new PasswordUtil().matches(password, user.getPassword());
            if (flag) {
                teamUsers.add(user);
                map.put("code",1);
                map.put("msg","OK");
                map.put("data", user.getUsername());
            }else{
                map.put("msg","用户名密码错误！");
            }
        }
        return map;
    }

    /**
     * 0 保存失败 1 保存成功
     *
     * @param task
     * @return
     */
    @PostMapping("/task/complete")
    public Map<String, Object> save(Task task) {
        Map<String, Object> map = new HashMap<>();
        if (task.getId() != null) {
            task.setTaskStatus(4);
            taskRepository.save(task);
            map.put("result", 1);
        } else {
            //输入数据有误
            map.put("result", 0);
        }
        return map;
    }


}
