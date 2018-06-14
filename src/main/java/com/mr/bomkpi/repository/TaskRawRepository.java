package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.TaskRaw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRawRepository extends JpaRepository<TaskRaw,Long> {
}
