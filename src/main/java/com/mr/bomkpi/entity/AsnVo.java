package com.mr.bomkpi.entity;

import org.springframework.context.annotation.Configuration;
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
public class AsnVo implements Serializable {
    @Id
    @Column(name = "asn_id")
    private String Id;

    @Column(name = "asn_nbr")
    private String asnNbr;

    @Column(name = "po_nbr")
    private String poNbr;

    @Column(name = "asn_type")
    private String asnType;

    @Column(name = "ord_pallet_qty")
    private Integer ordPalletQty;

    @Column(name = "ord_unit_qty")
    private Double ordUnitQty;

    @Column(name = "send_unit_qty")
    private Double sendUnitQty;

    @Column(name = "rcv_pallet_qty")
    private Integer rcvpalletQty;

    @Column(name = "rcv_unit_qty")
    private Double rcvUnitQty;

    @Column(name = "whse_code")
    private String whseCode;

    @Column(name = "fst_rcv_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fstRcvDate;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  creationDate;

    @Column(name = "last_modify_date")
    private Date lastModifyDate;

    private String status;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAsnNbr() {
        return asnNbr;
    }

    public void setAsnNbr(String asnNbr) {
        this.asnNbr = asnNbr;
    }

    public String getPoNbr() {
        return poNbr;
    }

    public void setPoNbr(String poNbr) {
        this.poNbr = poNbr;
    }

    public String getAsnType() {
        return asnType;
    }

    public void setAsnType(String asnType) {
        this.asnType = asnType;
    }

    public Integer getOrdPalletQty() {
        return ordPalletQty;
    }

    public void setOrdPalletQty(Integer ordPalletQty) {
        this.ordPalletQty = ordPalletQty;
    }

    public Double getOrdUnitQty() {
        return ordUnitQty;
    }

    public void setOrdUnitQty(Double ordUnitQty) {
        this.ordUnitQty = ordUnitQty;
    }

    public Double getSendUnitQty() {
        return sendUnitQty;
    }

    public void setSendUnitQty(Double sendUnitQty) {
        this.sendUnitQty = sendUnitQty;
    }

    public Integer getRcvpalletQty() {
        return rcvpalletQty;
    }

    public void setRcvpalletQty(Integer rcvpalletQty) {
        this.rcvpalletQty = rcvpalletQty;
    }

    public Double getRcvUnitQty() {
        return rcvUnitQty;
    }

    public void setRcvUnitQty(Double rcvUnitQty) {
        this.rcvUnitQty = rcvUnitQty;
    }

    public String getWhseCode() {
        return whseCode;
    }

    public void setWhseCode(String whseCode) {
        this.whseCode = whseCode;
    }

    public Date getFstRcvDate() {
        return fstRcvDate;
    }

    public void setFstRcvDate(Date fstRcvDate) {
        this.fstRcvDate = fstRcvDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
