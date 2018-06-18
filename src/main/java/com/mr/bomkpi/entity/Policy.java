package com.mr.bomkpi.entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "bm_policy")
public class Policy implements Serializable {

    @Id
    @Column(name = "POLICY_ID")
    private String policyId;

    @Column(name = "POLICY_NAME")
    private String policyName;


    @Column(name = "POLICY_DESC")
    private String policyDesc;


    @Column(name = "POLICY_TYPE")
    private Integer policyType;


    @Column(name = "RESOURCE_ID")
    private String resourceId;


    @Column(name = "CREATION_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


    @Column(name = "LAST_MODIFY_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifyDate;


    @Column(name = "creator")
    private String creator;


    @Column(name = "modified_by")
    private String modifyBy;


    @Column(name = "status")
    private Integer status;


    public Policy() {
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyDesc() {
        return policyDesc;
    }

    public void setPolicyDesc(String policyDesc) {
        this.policyDesc = policyDesc;
    }

    public Integer getPolicyType() {
        return policyType;
    }

    public void setPolicyType(Integer policyType) {
        this.policyType = policyType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
