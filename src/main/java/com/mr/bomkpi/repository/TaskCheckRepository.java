package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.TaskCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCheckRepository extends JpaRepository<TaskCheck,Long> {
}
