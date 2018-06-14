package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.KPIdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KpidataRepository extends JpaRepository<KPIdata,Long> {
}
