package com.mr.bomkpi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 加工子任务
 *
 * @author Administrator
 */
@Entity
@Table(name = "bom_task")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 子任务编码
     */
    @Column(name = "task_code")
    private String taskCode;
    /**
     * 子任务创建人
     */
    @ManyToOne(cascade = {},fetch = FetchType.LAZY)
    @JoinColumn(name = "create_user_code")
    private User createrCode;

    /**
     * 子任务创建时间
     */
    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    /**
     * 领取子任务时间
     */
    @Column(name = "requester_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date requesterDate;

    /**
     * 领取任务人编码
     */
    @Column(name = "requester_code")
    private String requester;
    /**
     * 子任务完成时间
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
     * 子任务产成品目标数量
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
    @ManyToMany(cascade = {},fetch = FetchType.LAZY)
    @JoinColumn(name = "counter_code")
    private List<TaskCounter> taskCounters;

    /**
     * 加工单
     */
    @ManyToOne(cascade = {},fetch = FetchType.LAZY)
    @JoinColumn(name = "task_order_code")
    private TaskOrder taskOrder;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public User getCreaterCode() {
        return createrCode;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreaterCode(User createrCode) {
        this.createrCode = createrCode;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getRequesterDate() {
        return requesterDate;
    }

    public void setRequesterDate(Date requesterDate) {
        this.requesterDate = requesterDate;
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


    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public List<TaskCounter> getTaskCounters() {
        return taskCounters;
    }

    public void setTaskCounters(List<TaskCounter> taskCounters) {
        this.taskCounters = taskCounters;
    }

    public TaskOrder getTaskOrder() {
        return taskOrder;
    }

    public void setTaskOrder(TaskOrder taskOrder) {
        this.taskOrder = taskOrder;
    }
}
