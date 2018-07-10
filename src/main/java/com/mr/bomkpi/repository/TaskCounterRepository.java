package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.TaskCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface TaskCounterRepository extends JpaRepository<TaskCounter,Long> {

    boolean existsByCounterCodeAndWhseCode(String counterCode, String whseCode);
    TaskCounter readByCounterCodeAndWhseCode(String counterCode, String whseCode);
    List<TaskCounter> findAllByWhseCode(String whseCode);

    @Query(value = "select * from bom_taskcounter  where ?1 ", nativeQuery = true)
    List<TaskCounter> findAllByConditon(String condition);

}
