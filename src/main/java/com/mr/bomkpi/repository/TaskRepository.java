package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface TaskRepository extends JpaRepository<Task,String> {
      Task  findByTaskCode(String taskcode);

      List<Task> findAllByTaskStatusOrderByRequesterDateDesc(Integer status);
}
