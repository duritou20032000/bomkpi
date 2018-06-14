package com.mr.bomkpi.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 其他工作的绩效数据
 * @author Administrator
 */
@Entity
@Table(name ="bom_kpiotherdata")
public class KPIotherData implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "whse_code")
    private String whseCode    ;

    @Column(name = "whse_name")
    private String  whseName;
    /**
     * 工作时间
     */
    @Column(name = "work_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date workDate;

    /**
     * 工作描述
     */
    @Column(name = "work_descrip")
    private String  workDesc;

    /**
     * 开始时间
     */
    @Column(name = "begin_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate  ;

    /**
     * 结束时间
     */
    @Column(name = "end_date")
    private String  endDate;

    /**
     *间断，主要是为请假，中途离去等
     */
    private String  interrupted ;
    /**
     * 用户
     */
    @ManyToOne(cascade = {},fetch = FetchType.EAGER)
    @JoinColumn(name = "user_code")
    private User user;

    public KPIotherData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getInterrupted() {
        return interrupted;
    }

    public void setInterrupted(String interrupted) {
        this.interrupted = interrupted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
