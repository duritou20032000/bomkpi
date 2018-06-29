package com.mr.bomkpi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 入库订单数据
 *
 * @author Administrator
 */
@Entity
@Table(name = "wms_asn_hdr")
public class AsnDtlVo implements Serializable {

    @Id
    @Column(name = "asn_id")
    private String Id;

    @Column(name = "whse_code")
    private String whseCode;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Transient
    private String whseName;


    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  creationDate;

    @Column(name = "fst_rcv_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fstRcvDate;

    @Column(name = "last_modify_date")
    private Date lastModifyDate;

    private String status;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getWhseCode() {
        return whseCode;
    }

    public void setWhseCode(String whseCode) {
        this.whseCode = whseCode;
    }

    public String getWhseName() {
        return whseName;
    }

    public void setWhseName(String whseName) {
        this.whseName = whseName;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getFstRcvDate() {
        return fstRcvDate;
    }

    public void setFstRcvDate(Date fstRcvDate) {
        this.fstRcvDate = fstRcvDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
