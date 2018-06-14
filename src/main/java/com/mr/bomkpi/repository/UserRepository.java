package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface UserRepository extends JpaRepository<User,String> {

     User  findByUsername(String username);
}
