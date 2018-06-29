package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.*;
import com.mr.bomkpi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class KPIController {

    @Autowired
    private AsnDtlVoRepository asnDtlVoRepository;

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

    @Autowired
    private LpnHdrVoRepository lpnHdrVoRepository;

    @Autowired
    private PktHdrVoRepository pktHdrVoRepository;

    @GetMapping("/kpi/getKpis")
    public String getKpis(Model model, Principal principal, String date) {

        List<AsnDtlVo> asns = asnDtlVoRepository.countSKU(principal.getName(),"2018-06-13");

        List<LpnHdrVo> lpns = lpnHdrVoRepository.querylpnHdrVos(principal.getName(),"2018-06-13");

        List<PktHdrVo> pkts = pktHdrVoRepository.queryPktHdrVos(principal.getName(),"2018-06-13");

        List<KPIdata> recorders = kpidataRepository.findAll();

        List<KPIotherData> oRecorders = kpiotherDataRepository.findAll();

        List<KPIcaculation> cRecorders = kpiCaculationRepository.findAll();

        model.addAttribute("asns", asns);
        model.addAttribute("lpns", lpns);
        model.addAttribute("pkts", pkts);
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