package com.mr.bomkpi.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 作业环节
 * @author Administrator
 */
@Entity
@Table(name = "bom_worknode")
public class Worknode implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 作业环节编码
     */
    @Column(name = "node_code")
    private Long nodeCode;

    @Column(name = "node_name")
    private String   nodeName;

    @Column(name = "node_status")
    private String  nodeStatus;
    /**
     * 仓库
     */
    @Column(name = "whse_code")
    private String whseCode    ;

    @Column(name = "whse_name")
    private String  whseName;

    private String  description;

    public Worknode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(Long nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
