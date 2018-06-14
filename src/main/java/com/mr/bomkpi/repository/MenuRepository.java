package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu,String> {


}