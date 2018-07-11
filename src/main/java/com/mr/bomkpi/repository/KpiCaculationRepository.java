package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.KPIcaculation;
import com.mr.bomkpi.entity.TaskOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KpiCaculationRepository extends JpaRepository<KPIcaculation,Long> ,JpaSpecificationExecutor<TaskOrder> {
}
