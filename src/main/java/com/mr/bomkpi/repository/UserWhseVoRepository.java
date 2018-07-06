package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.UserWhseVo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserWhseVoRepository extends JpaRepository<UserWhseVo, String> {

     List<UserWhseVo>  findAllByUserId(String userId);
     List<UserWhseVo>  findAllByUserName(String username,Sort sort);


}



