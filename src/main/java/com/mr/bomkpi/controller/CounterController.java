package com.mr.bomkpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Administrator
 */
@Controller
public class CounterController {
//
//    @Autowired
//    private CounterService counterService;

    @GetMapping("/counter/getCounters")
    public String getCounter(Model model){
//        List<TaskCounter> counters =  counterService.queryList();
//        model.addAttribute("counters",counters);
        return "/counter/list";
    }


}
