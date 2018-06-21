package com.mr.bomkpi.controller;

import com.mr.bomkpi.entity.KPIcaculation;
import com.mr.bomkpi.entity.KPIdata;
import com.mr.bomkpi.entity.KPIotherData;
import com.mr.bomkpi.repository.KpiCaculationRepository;
import com.mr.bomkpi.repository.KpidataRepository;
import com.mr.bomkpi.repository.KpiotherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class KPIController {

    @Autowired
    private KpidataRepository kpidataRepository;

    @Autowired
    private KpiCaculationRepository kpiCaculationRepository;

    @Autowired
    private KpiotherDataRepository kpiotherDataRepository;

    @GetMapping("/kpi/getKpis")
    public String getKpis(Model model){

        List<KPIdata> recorders =  kpidataRepository.findAll();

        List<KPIotherData> oRecorders = kpiotherDataRepository.findAll();

        List<KPIcaculation> cRecorders = kpiCaculationRepository.findAll();

        model.addAttribute("recorders",recorders);
        model.addAttribute("oRecorders",oRecorders);
        model.addAttribute("cRecorders",cRecorders);

        return "kpi/list";
    }
}
