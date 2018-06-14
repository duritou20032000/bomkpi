package com.mr.bomkpi.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 加工原料
 *
 * @author Administrator
 */
@Entity
@Table(name = "bom_taskraw")
public class TaskRaw implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 原料编码
     */
    @Column(name = "raw_code")
    private String rawCode;
    /**
     * 原料名称
     */
    @Column(name = "raw_name")
    private String rawName;
    /**
     * 原料数量
     */
    @Column(name = "raw_count")
    private String rawCount;
    /**
     * 原料单位
     */
    @Column(name = "raw_unit")
    private String rawUnit;

    /**
     * 加工单编码
     */
    @ManyToOne(cascade = {},fetch = FetchType.EAGER)
    @JoinColumn(name = "worksheet_code")
    private TaskOrder taskOrder;

    public TaskRaw() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRawCode() {
        return rawCode;
    }

    public void setRawCode(String rawCode) {
        this.rawCode = rawCode;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public String getRawCount() {
        return rawCount;
    }

    public void setRawCount(String rawCount) {
        this.rawCount = rawCount;
    }

    public String getRawUnit() {
        return rawUnit;
    }

    public void setRawUnit(String rawUnit) {
        this.rawUnit = rawUnit;
    }

    public TaskOrder getTaskOrder() {
        return taskOrder;
    }

    public void setTaskOrder(TaskOrder taskOrder) {
        this.taskOrder = taskOrder;
    }
}
