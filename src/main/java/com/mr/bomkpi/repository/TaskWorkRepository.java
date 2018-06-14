package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskWorkRepository extends JpaRepository<Task,Long> {
}
