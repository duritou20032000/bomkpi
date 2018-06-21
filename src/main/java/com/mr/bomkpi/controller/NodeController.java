package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.Worknode;
import com.mr.bomkpi.repository.WorknodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NodeController {

    @Autowired
    private WorknodeRepository worknodeRepository;

    @GetMapping("/kpinode/getNodes")
    public String getNodes(Model model){
        List<Worknode> nodes = worknodeRepository.findAll();
        model.addAttribute("nodes",nodes);
        return "kpi/kpinode";
    }
}
