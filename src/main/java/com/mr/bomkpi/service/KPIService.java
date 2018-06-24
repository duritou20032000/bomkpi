package com.mr.bomkpi.service;

import com.mr.bomkpi.repository.KpiCaculationRepository;
import com.mr.bomkpi.repository.KpidataRepository;
import com.mr.bomkpi.repository.KpiotherDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KPIService {

    public static Logger logger = LoggerFactory.getLogger(KPIService.class);

    /**
     * 获得多有的业绩指标的数据，组成数组回传给前端，然后用图表来进行展示.
     *                  asn_type         wms_asn_dtl
     * 采购入库按SKU                                    根据采购入库单找到仓库，通过仓库的
     * 退货入库 按SKU
     * 上架
     * 拣货
     * 包装
     * 装车
     * 制单员使用按单计算
     */










}
