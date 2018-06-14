package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface ResourcesRepository extends JpaRepository<Resources,String> {

    Resources findByName(String name);

    List<Resources> findAll();

    List<Resources> findAllBySubjectGroupId(String subjectGroupId);

}
