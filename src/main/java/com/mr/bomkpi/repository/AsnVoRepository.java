package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.AsnVo;
import com.mr.bomkpi.entity.Worknode;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AsnVoRepository extends JpaRepository<AsnVo,String> {


}


