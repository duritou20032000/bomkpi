package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.LpnHdrVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface LpnHdrVoRepository extends JpaRepository<LpnHdrVo, String> {

    //-- 用户上架完成的商品列表
    @Query(value = "select  * from wms_lpn_hdr where `status`='50' and modified_by = ?1 and last_modify_date like ?2% ", nativeQuery = true)
    List<LpnHdrVo>  querylpnHdrVos(String modified_by, String date);



}



