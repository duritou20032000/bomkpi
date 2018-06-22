package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.KPIcaculation;
import com.mr.bomkpi.entity.KPIdata;
import com.mr.bomkpi.entity.KPIotherData;
import com.mr.bomkpi.entity.Worknode;
import com.mr.bomkpi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class KPIController {

    @Autowired
    private KpidataRepository kpidataRepository;

    @Autowired
    private KpiCaculationRepository kpiCaculationRepository;

    @Autowired
    private KpiotherDataRepository kpiotherDataRepository;

    @Autowired
    private WorknodeRepository worknodeRepository;

    @Autowired
    private WhseRepository whseRepository;

    @GetMapping("/kpi/getKpis")
    public String getKpis(Model model) {

        List<KPIdata> recorders = kpidataRepository.findAll();

        List<KPIotherData> oRecorders = kpiotherDataRepository.findAll();

        List<KPIcaculation> cRecorders = kpiCaculationRepository.findAll();

        model.addAttribute("recorders", recorders);
        model.addAttribute("oRecorders", oRecorders);
        model.addAttribute("cRecorders", cRecorders);

        return "kpi/list";
    }


    @GetMapping("/kpi/form")
    public String form(Model model) {

        return "kpi/form";
    }


    @PostMapping("/kpi/addNode")
    public String addNode(Model model, Worknode node) {

        worknodeRepository.save(node);

        return "redirect:/kpi/getWorkNodes";
    }


    @GetMapping("/kpinode/getNodes")
    public String getNodes(Model model){
        List<Worknode> nodes = worknodeRepository.findAll();
        model.addAttribute("nodes",nodes);
        return "kpi/kpinode";
    }

    @GetMapping("/kpi/getWorkNodes")
    public String getWorkNodes(Model model) {

        List<Worknode> nodes = worknodeRepository.findAll();
        model.addAttribute("nodes", nodes);
        return "kpi/kpinode";
    }




}
