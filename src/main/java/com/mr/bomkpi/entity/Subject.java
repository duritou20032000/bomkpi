package com.mr.bomkpi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bm_subject")
public class Subject implements Serializable {
    @Id
    @Column(name = "SUBJECT_ID")
    private String subjectId;

    @Column(name = "SUBJECT_NAME")
    private String subjectName;

    @Column(name = "SUBJECT_DESC")
    private String subjectDesc;

    @Column(name = "SUBJECT_TYPE")
    private Integer subjectType;

    @Column(name = "CREATION_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "LAST_MODIFY_DATE")
    private Date lastModifyDate;

    private String creator;

    private Integer status;

    @Column(name = "subject_group_id")
    private String subjectGroupId;

    @ManyToMany(cascade = {},fetch =  FetchType.EAGER)
    @JoinTable(name = "bm_subject_group_connector",joinColumns = {@JoinColumn(name = "SUBJECT_ID")},inverseJoinColumns = {@JoinColumn(name = "SUBJECT_GROUP_ID")})
    private List<SubjectGroup> subjectGroups;

    public Subject() {
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public List<SubjectGroup> getSubjectGroups() {
        return subjectGroups;
    }

    public void setSubjectGroups(List<SubjectGroup> subjectGroups) {
        this.subjectGroups = subjectGroups;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    public Integer getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(Integer subjectType) {
        this.subjectType = subjectType;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
