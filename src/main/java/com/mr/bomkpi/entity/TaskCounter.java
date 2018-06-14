package com.mr.bomkpi.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 加工任务分配的柜台
 * @author Administrator
 */
@Entity
@Table(name = "bom_taskcounter",uniqueConstraints = {@UniqueConstraint(columnNames = {"whse_code","counter_code"})})
public class TaskCounter implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 仓库编码
     */
    @Column(name = "whse_code",length = 64)
    private String whseCode;
    /**
     * 仓库名称
     */
    @Column(name = "whse_name")
    private String  whseName ;
    /**
     * 柜台编码
     */
    @Column(name = "counter_code",length = 64)
    private String   counterCode;

    /**
     * 柜台名称
     */
    @Column(name = "counter_name")

    private String  counterName ;
    /**
     * 柜台状态 1 空闲 2使用中
     */
    @Column(name = "counter_status")
    private String  CounterStatus ;
    /**
     * 说明
     */
    private String   description;

    public TaskCounter() {
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

    public String getCounterCode() {
        return counterCode;
    }

    public void setCounterCode(String counterCode) {
        this.counterCode = counterCode;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getCounterStatus() {
        return CounterStatus;
    }

    public void setCounterStatus(String counterStatus) {
        CounterStatus = counterStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
