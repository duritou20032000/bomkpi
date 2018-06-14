package com.mr.bomkpi.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 绩效数据　
 * @author Administrator
 */
@Entity
@Table(name = "bom_kpidata")
public class KPIdata implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 绩效记录编码
     */
    @OneToOne
    @JoinColumn(name = "node_code")
    private Worknode worknode;
    /**
     * 用户
     */
    @ManyToOne(cascade = {},fetch = FetchType.EAGER)
    @JoinColumn(name = "user_code")
    private User user;
    /**
     * 绩效同步时间
     */
    @Column(name = "sync_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date syncDate;
    /**
     * 按件计数的件数
     */
    @Column(name = "item_count")
    private Float  itemCount;
    /**
     * 按SKU计数的sku数量
     */
    @Column(name="SKU_count")
    private Float SKUCount;
    /**
     * 按单计数的单数
     */
    @Column(name = "order_count")
    private Float  orderCount;
    /**
     *
     */
    private Float interrupted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Worknode getWorknode() {
        return worknode;
    }

    public void setWorknode(Worknode worknode) {
        this.worknode = worknode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    public Float getItemCount() {
        return itemCount;
    }

    public void setItemCount(Float itemCount) {
        this.itemCount = itemCount;
    }

    public Float getSKUCount() {
        return SKUCount;
    }

    public void setSKUCount(Float SKUCount) {
        this.SKUCount = SKUCount;
    }

    public Float getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Float orderCount) {
        this.orderCount = orderCount;
    }

    public Float getInterrupted() {
        return interrupted;
    }

    public void setInterrupted(Float interrupted) {
        this.interrupted = interrupted;
    }
}
