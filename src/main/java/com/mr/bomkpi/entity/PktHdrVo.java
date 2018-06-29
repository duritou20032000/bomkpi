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
@Table(name = "wms_pkt_hdr")
public class PktHdrVo implements Serializable {

    @Id
    @Column(name = "pkt_id")
    private String Id;

    @Column(name = "pkt_nbr")
    private String pktnbr;

    @Column(name = "whse_code")
    private String whseCode;


    @Transient
    private String whseName;

    @Column(name = "pkt_do_qty")
    private String pktDoQty;


    @Column(name = "pkt_wave_do_qty")
    private String pktWaveDoQty;

    @Column(name = "pkt_type")
    private String pktType;

    @Column(name = "invoice_type")
    private String invoiceType;

    @Column(name = "vol_type")
    private String volType;

    @Column(name = "temper_zone")
    private String temperZone;


    @Column(name = "pick_start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @Column(name = "pick_end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPktnbr() {
        return pktnbr;
    }

    public void setPktnbr(String pktnbr) {
        this.pktnbr = pktnbr;
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

    public String getPktDoQty() {
        return pktDoQty;
    }

    public void setPktDoQty(String pktDoQty) {
        this.pktDoQty = pktDoQty;
    }

    public String getPktWaveDoQty() {
        return pktWaveDoQty;
    }

    public void setPktWaveDoQty(String pktWaveDoQty) {
        this.pktWaveDoQty = pktWaveDoQty;
    }

    public String getPktType() {
        return pktType;
    }

    public void setPktType(String pktType) {
        this.pktType = pktType;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getVolType() {
        return volType;
    }

    public void setVolType(String volType) {
        this.volType = volType;
    }

    public String getTemperZone() {
        return temperZone;
    }

    public void setTemperZone(String temperZone) {
        this.temperZone = temperZone;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}


