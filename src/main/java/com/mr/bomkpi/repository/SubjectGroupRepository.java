package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.SubjectGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface SubjectGroupRepository extends JpaRepository<SubjectGroup,String> {

}
