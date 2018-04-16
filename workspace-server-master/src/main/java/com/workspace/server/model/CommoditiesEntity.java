package com.workspace.server.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "commodities", schema = "shiro", catalog = "")
public class CommoditiesEntity {
    private int cid;
    private String cName;
    private String cLink;
    private int cLowestPrice;
    private String cCount;
    private String cPicture;
   // private int clowestPrice;

    @Id
    @Column(name = "CID", nullable = false)
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "CName", nullable = false, length = -1)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "CLink", nullable = false, length = -1)
    public String getcLink() {
        return cLink;
    }

    public void setcLink(String cLink) {
        this.cLink = cLink;
    }

    @Basic
    @Column(name = "CLowestPrice", nullable = false, precision = 0)
    public int getcLowestPrice() {
        return cLowestPrice;
    }

    public void setcLowestPrice(int cLowestPrice) {
        this.cLowestPrice = cLowestPrice;
    }

    @Basic
    @Column(name = "CCount", nullable = false, length = 20)
    public String getcCount() {
        return cCount;
    }

    public void setcCount(String cCount) {
        this.cCount = cCount;
    }

    @Basic
    @Column(name = "CPicture", nullable = false, length = 255)
    public String getcPicture() {
        return cPicture;
    }

    public void setcPicture(String cPicture) {
        this.cPicture = cPicture;
    }

//    @Basic
//    @Column(name = "clowest_price", nullable = false, precision = 0)
//    public int getClowestPrice() {
//        return clowestPrice;
//    }
//
//    public void setClowestPrice(int clowestPrice) {
//        this.clowestPrice = clowestPrice;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommoditiesEntity that = (CommoditiesEntity) o;
        return cid == that.cid &&
                cLowestPrice == that.cLowestPrice &&
//                clowestPrice == that.clowestPrice &&
                Objects.equals(cName, that.cName) &&
                Objects.equals(cLink, that.cLink) &&
                Objects.equals(cCount, that.cCount) &&
                Objects.equals(cPicture, that.cPicture);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cid, cName, cLink, cLowestPrice, cCount, cPicture);//, clowestPrice
    }
}
