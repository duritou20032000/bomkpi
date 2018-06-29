package com.mr.bomkpi.repository;

import com.mr.bomkpi.entity.AsnDtlVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsnDtlVoRepository extends JpaRepository<AsnDtlVo, String> {

    //用户入库的SKU数
    @Query(value = "select * from wms_asn_dtl  where modified_by=?1 and creation_date like ?2%", nativeQuery = true)
    List<AsnDtlVo>   countSKU(String modifiedBy, String date);


}



