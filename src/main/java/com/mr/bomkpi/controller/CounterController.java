package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.TaskCounter;
import com.mr.bomkpi.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Administrator
 */
@Controller
public class CounterController {

    @Autowired
    private CounterService counterService;

    @GetMapping("/counter/getCounters")
    public String getCounter(Model model){
        List<TaskCounter> counters =  counterService.queryList();
        model.addAttribute("counters",counters);
        return "/counter/list";
    }
    @GetMapping("/counter/form")
    public String addCounter(Model model,String counterId){

        return "/counter/form";
    }

}
