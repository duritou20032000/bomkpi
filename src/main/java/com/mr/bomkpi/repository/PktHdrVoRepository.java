package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.PktHdrVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface PktHdrVoRepository extends JpaRepository<PktHdrVo, String> {

    //-- 用户拣货完成的商品列表
    @Query(value = "select  * from wms_pkt_hdr where `status`='30' and picker = ?1 and pick_end_time like ?2% ", nativeQuery = true)
    List<PktHdrVo>  queryPktHdrVos(String picker, String date);


}



