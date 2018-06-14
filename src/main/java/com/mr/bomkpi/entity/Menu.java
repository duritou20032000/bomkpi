package com.mr.bomkpi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Jonsy
 * 菜单
 */
@Entity
public class Menu extends TreeModel implements Serializable{
    @Id
    private String id;

    private String label;

    private String path="0";  //父节点的路径与父节点的id路径，用","分开，0表示父节点是根节点

    @Column(name = "`order`")
    private int order=1;  //排序

    private Integer type;//扩展字段。菜单类型，供不同业务区分

    private String style;//样式，方便ui展现

    /** 状态 是否禁用*/
    private boolean disabled;
    /**链接*/
    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
