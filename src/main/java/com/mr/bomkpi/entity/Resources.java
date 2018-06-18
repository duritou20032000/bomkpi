package com.mr.bomkpi.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 只是在没整合前测试用
 */
@Entity
@Table(name = "bom_resource")
public class Resources implements Serializable {
    @Id
    @Column(name = "resource_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "resURL")
    private String url;


    @Column(name = "type")
    private Integer type;

    @Column(name = "subject_group_id")
    private String subjectGroupId;

    public Resources() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSubjectGroupId() {
        return subjectGroupId;
    }

    public void setSubjectGroupId(String subjectGroupId) {
        this.subjectGroupId = subjectGroupId;
    }
}
