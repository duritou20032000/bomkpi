package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.*;
import com.mr.bomkpi.repository.*;
import com.mr.bomkpi.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.ArrayList;
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

    @Autowired
    private AsnVoRepository asnVoRepository;

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
    public String getNodes(Model model) {
        List<Worknode> nodes = worknodeRepository.findAll();
        model.addAttribute("nodes", nodes);
        return "kpi/kpinode";
    }

    @GetMapping("/kpi/getWorkNodes")
    public String getWorkNodes(Model model) {

        List<Worknode> nodes = worknodeRepository.findAll();
        model.addAttribute("nodes", nodes);
        return "kpi/kpinode";
    }

    @GetMapping("/kpi/asns")
    @ResponseBody
    public List<AsnVo> getAsns() {
        List result = new ArrayList();
        Sort sort = new Sort(Sort.Direction.DESC, "lastModifyDate");
//        List<AsnVo> asns = asnVoRepository.findAllByLastModifyDateLikeAndStatus(sort, Date.valueOf("2018-06-17"),90);
        return result;
    }


}