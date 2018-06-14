package com.mr.bomkpi.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 质检项目
 * @author Administrator
 */
@Entity
@Table(name = "bom_taskitem")
public class TaskItem implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 质检任务编码
     */
    @ManyToOne(cascade = {},fetch = FetchType.EAGER)
    @JoinColumn(name = "chektask_code")
    private Task task;
    /**
     * 质检项目
     */
    private String item;
    /**
     * 质检项目状态
     * 0  未删除  1 删除
     */
    @Column(name = "check_status")
    private String  checkStatus;

    private String  description;

    public TaskItem() {
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
