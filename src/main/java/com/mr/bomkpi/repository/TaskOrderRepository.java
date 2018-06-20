package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.TaskOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface TaskOrderRepository extends JpaRepository<TaskOrder,Long> {

}
