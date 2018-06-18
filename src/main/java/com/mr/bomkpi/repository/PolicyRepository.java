package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.Policy;
import com.mr.bomkpi.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface PolicyRepository extends JpaRepository<Policy,String> {
}
