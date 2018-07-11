package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.KPIdata;
import com.mr.bomkpi.entity.TaskOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KpidataRepository extends JpaRepository<KPIdata,Long> ,JpaSpecificationExecutor<TaskOrder> {
}
