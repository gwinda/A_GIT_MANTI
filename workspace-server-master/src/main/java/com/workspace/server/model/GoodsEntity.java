package com.workspace.server.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "goods", schema = "shiro", catalog = "")
public class GoodsEntity {
    private int gId;
    private String gUrl;
    private String gName;
    private Double gprice;
    private String gRank;

    @Id
    @Column(name = "GId")
    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    @Basic
    @Column(name = "GUrl")
    public String getgUrl() {
        return gUrl;
    }

    public void setgUrl(String gUrl) {
        this.gUrl = gUrl;
    }

    @Basic
    @Column(name = "GName")
    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    @Basic
    @Column(name = "Gprice")
    public Double getGprice() {
        return gprice;
    }

    public void setGprice(Double gprice) {
        this.gprice = gprice;
    }

    @Basic
    @Column(name = "GRank")
    public String getgRank() {
        return gRank;
    }

    public void setgRank(String gRank) {
        this.gRank = gRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsEntity that = (GoodsEntity) o;
        return gId == that.gId &&
                Objects.equals(gUrl, that.gUrl) &&
                Objects.equals(gName, that.gName) &&
                Objects.equals(gprice, that.gprice) &&
                Objects.equals(gRank, that.gRank);
    }

    @Override
    public int hashCode() {

        return Objects.hash(gId, gUrl, gName, gprice, gRank);
    }
}
