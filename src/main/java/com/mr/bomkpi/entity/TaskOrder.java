package com.mr.bomkpi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 加工单
 * @author Administrator
 */
@Entity
@Table(name = "bom_taskorder")
public class TaskOrder implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 仓库编码
     *
     */
    @Column(name = "whse_code")
    private String whseCode    ;
    /**
     * 仓库名称
     *
     */
    @Column(name = "whse_name")
    private String  whseName;
    /**
     * 加工单编码
     *
     */
    @Column(name ="work_order_code")
    private String  orderCode;
    /**
     * 加工单类型
     *1 组装 2 拆零
     */
    @Column(name ="work_order_type")
    private String  orderType;
    /**
     * 最后期限
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;

    /**
     * 同步时间
     *
     */
    @Column(name ="sync_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  syncDate;
    /**
     * 产成品编码
     *
     */
    @Column(name ="product_code")
    private String  productCode;

    /**
     * 产成品名称
     *
     */
    @Column(name ="product_name")
    private String  productName;
    /**
     * 产成品数量
     *
     */
    @Column(name ="product_count")
    private String  productCount;
    /**
     * 产成品单位
     *
     */
    @Column(name ="product_unit")
    private String  productUnit;


    /**
     *
     * 加工任务完成时间
     *
     */
    @Column(name ="finish_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishDate;

    /**
     * 加工任务状态
     * 1 未分配  2 已分配  3 已完成
     */
    @Column(name ="plugin_status")
    private Integer pluginStatus  ;

    /**
     * 关单人编码
     */
    @Column(name ="closer_code")
    private String  closerCode;
    /**
     *  关单人名称
     */
    @Column(name ="closer_name")
    private String  closerName;

    /**
     * 关单时间
     */
    @Column(name ="close_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeDate;

    public TaskOrder() {
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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
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

    public String getCloserCode() {
        return closerCode;
    }

    public void setCloserCode(String closerCode) {
        this.closerCode = closerCode;
    }

    public String getCloserName() {
        return closerName;
    }

    public void setCloserName(String closerName) {
        this.closerName = closerName;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getPluginStatus() {
        return pluginStatus;
    }

    public void setPluginStatus(Integer pluginStatus) {
        this.pluginStatus = pluginStatus;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
}
