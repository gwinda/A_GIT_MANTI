package com.workspace.server.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "commodities_log", schema = "shiro", catalog = "")
public class CommoditiesLogEntity {
    private int clId;
    private int cid;
    private int clPrice;
    private String clName;
    private int clCount;
    private Timestamp clDateTime;


    @Id
    @Column(name = "CLId")
    public int getClId() {
        return clId;
    }

    public void setClId(int clId) {
        this.clId = clId;
    }

    @Basic
    @Column(name = "CID")
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "CLPrice")
    public int getClPrice() {
        return clPrice;
    }

    public void setClPrice(int clPrice) {
        this.clPrice = clPrice;
    }

    @Basic
    @Column(name = "CLName")
    public String getClName() {
        return clName;
    }

    public void setClName(String clName) {
        this.clName = clName;
    }

    @Basic
    @Column(name = "CLCount")
    public int getClCount() {
        return clCount;
    }

    public void setClCount(int clCount) {
        this.clCount = clCount;
    }

    @Basic
    @Column(name = "CLDateTime")
    public Timestamp getClDateTime() {
        return clDateTime;
    }

    public void setClDateTime(Timestamp clDateTime) {
        this.clDateTime = clDateTime;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommoditiesLogEntity that = (CommoditiesLogEntity) o;
        return clId == that.clId &&
                cid == that.cid &&
                clPrice == that.clPrice &&
                clCount == that.clCount &&
                Objects.equals(clName, that.clName) &&
                Objects.equals(clDateTime, that.clDateTime);

    }

    @Override
    public int hashCode() {

        return Objects.hash(clId, cid, clPrice, clName, clCount, clDateTime);
    }
}
