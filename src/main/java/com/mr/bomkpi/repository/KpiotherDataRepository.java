package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.KPIotherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KpiotherDataRepository extends JpaRepository<KPIotherData,Long> {
}
