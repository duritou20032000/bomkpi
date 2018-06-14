package com.mr.bomkpi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="bm_user")
public class User implements Serializable{
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "user_type")
    private Integer userType;
    @Column(name = "PWD")
    private String password;

    private String realname;

    @Column(name = "WHSE_ID")
    private String whseId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "CREATION_DATE")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "LAST_MODIFY_DATE")
    private Date lastModifyDate;

    private String creator;

    @Column(name = "modified_by")
    private String modifiedBy;

    private Integer status;

    @Column(name = "vdr_id")
    private String vdrId;

    @Column(name = "vdr_name")
    private String vdrName;

    @Column(name = "vdr_code")
    private String vdrCode;

    @Column(name = "channel_client_Id")
    private String channelClientId;

    @Column(name = "channel_clinet_nbr")
    private String channelClinetNbr;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "channel_name")
    private String channelName;

    @Column(name = "distributor_id")
    private String distributorId;

    @Column(name = "distributor_code")
    private String distributorCode;

    @Column(name = "distributor_name")
    private String distributorName;

    @Column(name = "curr_loggin_whseid")
    private String currLogginWhseId;

    @Column(name = "curr_loggin_whsecode")
    private String currLogginWhseCode;

    @Column(name = "curr_loggin_whsename")
    private String currLogginWhseName;

    private String mobile;

    private String email;

    private String locale;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_change_pwd_time")
    private Date lastChangePwdTime;

    @OneToOne
    @JoinTable(name = "bm_user_subject",joinColumns = {@JoinColumn(name = "USER_ID")},inverseJoinColumns = {@JoinColumn(name = "SUBJECT_ID")})
    private Subject subject;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getWhseId() {
        return whseId;
    }

    public void setWhseId(String whseId) {
        this.whseId = whseId;
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

    public String getVdrId() {
        return vdrId;
    }

    public void setVdrId(String vdrId) {
        this.vdrId = vdrId;
    }

    public String getVdrName() {
        return vdrName;
    }

    public void setVdrName(String vdrName) {
        this.vdrName = vdrName;
    }

    public String getVdrCode() {
        return vdrCode;
    }

    public void setVdrCode(String vdrCode) {
        this.vdrCode = vdrCode;
    }

    public String getChannelClientId() {
        return channelClientId;
    }

    public void setChannelClientId(String channelClientId) {
        this.channelClientId = channelClientId;
    }

    public String getChannelClinetNbr() {
        return channelClinetNbr;
    }

    public void setChannelClinetNbr(String channelClinetNbr) {
        this.channelClinetNbr = channelClinetNbr;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
    }

    public String getDistributorCode() {
        return distributorCode;
    }

    public void setDistributorCode(String distributorCode) {
        this.distributorCode = distributorCode;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getCurrLogginWhseId() {
        return currLogginWhseId;
    }

    public void setCurrLogginWhseId(String currLogginWhseId) {
        this.currLogginWhseId = currLogginWhseId;
    }

    public String getCurrLogginWhseCode() {
        return currLogginWhseCode;
    }

    public void setCurrLogginWhseCode(String currLogginWhseCode) {
        this.currLogginWhseCode = currLogginWhseCode;
    }

    public String getCurrLogginWhseName() {
        return currLogginWhseName;
    }

    public void setCurrLogginWhseName(String currLogginWhseName) {
        this.currLogginWhseName = currLogginWhseName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Date getLastChangePwdTime() {
        return lastChangePwdTime;
    }

    public void setLastChangePwdTime(Date lastChangePwdTime) {
        this.lastChangePwdTime = lastChangePwdTime;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
