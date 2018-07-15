package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.*;
import com.mr.bomkpi.repository.*;
import com.mr.bomkpi.service.OrderService;
import com.mr.bomkpi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
public class OrderController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserWhseVoRepository userWhseVoRepository;
    @Autowired
    private TaskCounterRepository counterRepository;
    @Autowired
    private TaskOrderRepository orderRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private WhseRepository whseRepository;
    @Autowired
    private OrderService orderService;

    @PostMapping("/order/getOrders")
    public Map<String,List<TaskOrder>> getOrders(TaskOrder order,Principal principal,String fuzzy,String fuzzySearch) {
//        查找该用户所属仓库下的所有订单
        String username = principal.getName();
        Map<String, List<TaskOrder>> map = new HashMap<>();
        List<TaskOrder> orders = new ArrayList<>();
        List<UserWhseVo> userWhses = new ArrayList<>();
        if (!StringUtil.isEmptyOrNull(username)) {
            User user = userRepository.findByUsername(username);
            userWhses = userWhseVoRepository.findAllByUserId(user.getUserId());
        }

        orders = orderService.queryListOnCondition(order,userWhses,fuzzy,fuzzySearch);
        map.put("data",orders);
        return map;

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
    public Map<String,Object> generateTask( TaskOrder order,Object table, Principal principal){
        Map<String, Object> map = new HashMap<>();
        orderService.saveTasks(order,principal);
        map.put("code",1);
        map.put("msg","ok");
        return map;
    }

    /**
     * 测试添加订单
     * @param order
     */
    @PostMapping("/order/save")
    public void save(TaskOrder order){
        Whse whse = whseRepository.findByWhseCode(order.getWhseCode());
        if(whse != null){
            order.setWhseName(whse.getWhseName());
        }
        orderRepository.save(order);
    }
    @PostMapping("/order/delete")
    public void delete(String  id){
        if(!StringUtil.isEmptyOrNull(id)){
            orderRepository.deleteById(Long.valueOf(id));
        }
    }
    @PostMapping("/order/getWhseCoutners")
    public Map<String, Object> getWhseCoutners(String  whseCode){
        Map<String, Object> map = new HashMap<>();
        List<TaskCounter> counters = counterRepository.findAllByWhseCode(whseCode);
        if(counters.size()==0){
            map.put("code",0);
            map.put("msg","请先为该仓库创建柜台！");
        }else{
            map.put("code",1);
            map.put("msg","OK");
            map.put("data",counters);
        }
        return map;
    }
}
