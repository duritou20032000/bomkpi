package com.mr.bomkpi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 加工子任务
 *
 * @author Administrator
 */
@Entity
@Table(name = "bom_taskwork")
public class TaskWork implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 任务编码
     */
    @Column(name = "task_code")
    private String taskCode;
    /**
     * 工单编码
     */
    @Column(name = "worksheet_code")
    private String worksheetCode;
    /**
     * 仓库编码
     */
    @Column(name = "whse_code")
    private String whseCode;
    /**
     * 仓库名称
     */
    @Column(name = "whse_name")
    private String whseName;
    /**
     * 加工单类型
     * 1 组装 2 拆零
     */
    @Column(name = "worksheet_type")
    private Integer worksheetType;
    /**
     * 子任务创建时间
     */
    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    /**
     * 子任务创建人代码
     */
    @Column(name = "creater_code")
    private String createrCode;
    /**
     * 子任务创建任名称
     */
    @Column(name = "creater_name")
    private String createrName;
    /**
     * 领取任务时间
     */
    @Column(name = "requester_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date requesterDate;
    /**
     * 领取任务人编码
     */
    @Column(name = "requester_code")
    private String requesterCode;
    /**
     * 领取任务人名称
     */
    @Column(name = "requester_name")
    private String requesterName;
    /**
     * 完成时间
     */
    @Column(name = "finish_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishDate;
    /**
     * 产成品代码
     */
    @Column(name = "product_code")
    private String productCode;
    /**
     * 产成品名称
     */
    @Column(name = "product_name")
    private String productName;
    /**
     * 目标产成品数量
     */
    @Column(name = "product_count")
    private String productCount;
    /**
     * 产成品单位
     */
    @Column(name = "product_unit")
    private String productUnit;
    /**
     * 描述
     */
    private String description;
    /**
     * 任务状态
     * 1 未领取 2 已领取 3已完成
     */
    @Column(name = "task_status")
    private Integer taskStatus;
    /**
     * 柜台编码
     */
    @Column(name = "counter_code")
    private String counterCode;

    public TaskWork() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getWorksheetCode() {
        return worksheetCode;
    }

    public void setWorksheetCode(String worksheetCode) {
        this.worksheetCode = worksheetCode;
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

    public Integer getWorksheetType() {
        return worksheetType;
    }

    public void setWorksheetType(Integer worksheetType) {
        this.worksheetType = worksheetType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreaterCode() {
        return createrCode;
    }

    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public Date getRequesterDate() {
        return requesterDate;
    }

    public void setRequesterDate(Date requesterDate) {
        this.requesterDate = requesterDate;
    }

    public String getRequesterCode() {
        return requesterCode;
    }

    public void setRequesterCode(String requesterCode) {
        this.requesterCode = requesterCode;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getCounterCode() {
        return counterCode;
    }

    public void setCounterCode(String counterCode) {
        this.counterCode = counterCode;
    }
}
