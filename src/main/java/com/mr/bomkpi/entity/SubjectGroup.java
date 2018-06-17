package com.mr.bomkpi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bm_subject_group")
public class SubjectGroup implements Serializable {
    @Id
    @Column(name = "SUBJECT_GROUP_ID")
    private String   subjectGroupId;

    @Column(name = "SUBJECT_GROUP_NAME")
    private String   subjectGroupName;

    @Column(name = "SUBJECT_GROUP_DESC")
    private String   subjectGroupDesc;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "CREATION_DATE")
    private Date   creationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "LAST_MODIFY_DATE")
    private Date    lastModifyDate;

    private String   creator;

    @Column(name = "modified_by")
    private String   modifiedBy;

    private Integer   status;


    public SubjectGroup() {
    }

    public String getSubjectGroupId() {
        return subjectGroupId;
    }

    public void setSubjectGroupId(String subjectGroupId) {
        this.subjectGroupId = subjectGroupId;
    }

    public String getSubjectGroupName() {
        return subjectGroupName;
    }

    public void setSubjectGroupName(String subjectGroupName) {
        this.subjectGroupName = subjectGroupName;
    }

    public String getSubjectGroupDesc() {
        return subjectGroupDesc;
    }

    public void setSubjectGroupDesc(String subjectGroupDesc) {
        this.subjectGroupDesc = subjectGroupDesc;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
