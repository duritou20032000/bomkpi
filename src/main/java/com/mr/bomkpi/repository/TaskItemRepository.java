package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.TaskItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskItemRepository extends JpaRepository<TaskItem,Long> {
}
