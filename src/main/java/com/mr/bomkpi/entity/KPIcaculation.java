package com.mr.bomkpi.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 绩效计算参数
 * @author Administrator
 */
@Entity
@Table(name = "bom_kpicaculation")
public class KPIcaculation implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 计算类型
     */
    @Column(name = "calculation_type")
    private String   calculationType;
    /**
     * 大件物品
     */
    @Column(name = "large_item")
    private String   largeItem;
    /**
     * 小件物品
     */
    @Column(name = "samll_item")
    private String  samllItem ;
    /**
     * 正常物品
     */
    @Column(name = "normal_item")
    private String   nornalItem;
    /**
     * 状态
     */
    private String  status;
    /**
     * 说明
     */
    private String  moment ;

    /**
     * 绩效记录编码
     */
    @OneToMany(cascade = {},fetch = FetchType.EAGER)
    @JoinColumn(name = "node_code")
    private List<KPIdata> kpIdata;

    public KPIcaculation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<KPIdata> getKpIdata() {
        return kpIdata;
    }

    public void setKpIdata(List<KPIdata> kpIdata) {
        this.kpIdata = kpIdata;
    }

    public String getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(String calculationType) {
        this.calculationType = calculationType;
    }

    public String getLargeItem() {
        return largeItem;
    }

    public void setLargeItem(String largeItem) {
        this.largeItem = largeItem;
    }

    public String getSamllItem() {
        return samllItem;
    }

    public void setSamllItem(String samllItem) {
        this.samllItem = samllItem;
    }

    public String getNornalItem() {
        return nornalItem;
    }

    public void setNornalItem(String nornalItem) {
        this.nornalItem = nornalItem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }
}
