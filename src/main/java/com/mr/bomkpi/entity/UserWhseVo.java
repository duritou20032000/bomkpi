package com.mr.bomkpi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="wms_user_whse")
public class UserWhseVo implements Serializable {
    @Id
    @Column(name = "user_whse_id")
    private String id;

    @Column(name = "whse_id")
    private String whseId;

    @Column(name = "whse_code")
    private String whseCode;

    @Column(name = "whse_name")
    private String whseName;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "creation_date")
    private String creationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_modify_date")
    private String modifyDate;

    @Column(name = "creator")
    private String creator;

    @Column(name = "modified_by")
    private String modifyBy;

    @Column(name = "status")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWhseName() {
        return whseName;
    }

    public void setWhseName(String whseName) {
        this.whseName = whseName;
    }
}
