package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.AsnDtlVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsnDtlVoRepository extends JpaRepository<AsnDtlVo, String> {

    //用户入库的单数
    Long  countDistinctByModifiedByAAndLastModifyDateLike(String username);

//    //-- 昨日各仓库创建入库订单数
//    @Query(value = "select distinct count(distinct asncountvo0_.asn_id) as col_0_0_ from wms_asn_hdr asncountvo0_ where asncountvo0_.whse_code=?1 and (asncountvo0_.creation_date like ?2%)", nativeQuery = true)
//    Long countCreateAsn(String whseCode, String date);
//
//    //-- 昨日各仓库接收入库单数
//    @Query(value = "select distinct count(distinct asncountvo0_.asn_id) as col_0_0_ from wms_asn_hdr asncountvo0_ where asncountvo0_.whse_code=?1 and (asncountvo0_.last_modify_date like ?2%)", nativeQuery = true)
//    Long countAsn(String whseCode, String date);
//
//    //    -- 给管理层使用 昨日仓库调拨类型入库完成订单单数
//    @Query(value = "select distinct count(distinct asncountvo0_.asn_id) as col_0_0_ from wms_asn_hdr asncountvo0_ where asncountvo0_.whse_code=?1 and (asncountvo0_.last_modify_date like ?2%) and `status` = 90 AND asn_type = 30", nativeQuery = true)
//    Long countAsnFinished(String whseCode, String date);
//
//




}



