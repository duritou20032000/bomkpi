package com.mr.bomkpi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "wms_whse_master")
public class Whse implements Serializable {
    @Id
    @Column(name = "whse_id")
    private String whseId;

    @Column(name = "whse_code")
    private String whseCode;

    @Column(name = "whse_name")
    private String whseName;

    @Column(name = "addr")
    private String addr;

    @Column(name = "tel")
    private String tel;

    @Column(name = "whse_type")
    private String whseType;

    @Column(name = "status")
    private String status;

    public String getWhseId() {
        return whseId;
    }

    public void setWhseId(String whseId) {
        this.whseId = whseId;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWhseType() {
        return whseType;
    }

    public void setWhseType(String whseType) {
        this.whseType = whseType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
