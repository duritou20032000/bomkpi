package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.TaskOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksheetRepository extends JpaRepository<TaskOrder,Long> {
}
