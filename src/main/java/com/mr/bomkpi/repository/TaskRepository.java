package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.Task;
import com.mr.bomkpi.entity.TaskOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface TaskRepository extends JpaRepository<Task,String>,JpaSpecificationExecutor<Task> {
      Task  findByTaskCode(String taskcode);

      List<Task> findAllByTaskStatusOrderByRequesterDateDesc(Integer status);
}
