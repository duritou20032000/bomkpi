package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.Whse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface WhseRepository extends JpaRepository<Whse,String> {
    Whse findByWhseCode(String code);
}
