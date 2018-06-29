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
@Table(name = "wms_lpn_hdr")
public class LpnHdrVo implements Serializable {

    @Id
    @Column(name = "lpn_id")
    private String Id;

    @Column(name = "lpn_nbr")
    private String lpnnbr;

    @Column(name = "whse_code")
    private String whseCode;


    @Transient
    private String whseName;

    @Column(name = "ttl_qty")
    private String ttlQty;


    private String vol;

    private String wt;


    @Column(name = "invoice_type")
    private String invoiceType;

    @Column(name = "lpn_type")
    private String lpnType;

    @Column(name = "putway_qty")
    private String putwayQty;

    @Column(name = "putway_type")
    private String putwayType;

    private Integer status;


    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    @Column(name = "last_modify_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

    @Column(name = "modifiedBy")
    private String modifiedBy;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLpnnbr() {
        return lpnnbr;
    }

    public void setLpnnbr(String lpnnbr) {
        this.lpnnbr = lpnnbr;
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

    public String getTtlQty() {
        return ttlQty;
    }

    public void setTtlQty(String ttlQty) {
        this.ttlQty = ttlQty;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getWt() {
        return wt;
    }

    public void setWt(String wt) {
        this.wt = wt;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getLpnType() {
        return lpnType;
    }

    public void setLpnType(String lpnType) {
        this.lpnType = lpnType;
    }

    public String getPutwayQty() {
        return putwayQty;
    }

    public void setPutwayQty(String putwayQty) {
        this.putwayQty = putwayQty;
    }

    public String getPutwayType() {
        return putwayType;
    }

    public void setPutwayType(String putwayType) {
        this.putwayType = putwayType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}


