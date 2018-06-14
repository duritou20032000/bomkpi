package com.mr.bomkpi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 质检
 * @author Administrator
 */
@Entity
@Table(name = "bom_taskcheck")
public class TaskCheck implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 任务编码
     */
    @ManyToOne(cascade = {},fetch = FetchType.EAGER)
    @JoinColumn(name = "task_code")
    private Task task;
    /**
     * 质检员编码
     *
     */
    @ManyToMany(cascade = {},fetch = FetchType.EAGER)
    @JoinColumn(name = "checker_code")
    private List<User> checkers;

    /**
     * 产成品质检结果
     */
    @Column(name = "check_result")
    private String   checkResult;

    @Column(name = "check_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  checkDate;
    /**
     * 产成品质检描述
     */
    @Column(name = "check_desc")
    private String   checkDesc;
    /**
     * 质检状态
     * 0  未删除  1 删除
     */
    @Column(name = "check_status")
    private String   checkStatus;
    /**
     * 产成品质检补充说明
     */
    private String   moment;

    public TaskCheck() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<User> getCheckers() {
        return checkers;
    }

    public void setCheckers(List<User> checkers) {
        this.checkers = checkers;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }
}
