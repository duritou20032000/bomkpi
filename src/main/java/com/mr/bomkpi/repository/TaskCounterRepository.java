package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.TaskCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface TaskCounterRepository extends JpaRepository<TaskCounter,Long> {

    boolean existsByCounterCodeAndWhseCode(String counterCode, String whseCode);
    TaskCounter readByCounterCodeAndWhseCode(String counterCode, String whseCode);

}
