package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.Worknode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorknodeRepository extends JpaRepository<Worknode,Long> {
}
