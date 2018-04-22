package com.workspace.server.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by 邻家的小胖子 on 2018/4/22.
 */
@Entity
@Table(name = "usercommoditylog", schema = "shiro", catalog = "")
public class UsercommoditylogEntity {
    private int ucId;
    private int uId;
    private int cId;
    private int inFocus;
    private Timestamp startDate;
    private Timestamp cancellDate;

    public void setInFocus(int inFocus) {
        this.inFocus = inFocus;
    }

    @Id
    @Column(name = "UCId")
    public int getUcId() {
        return ucId;
    }

    public void setUcId(int ucId) {
        this.ucId = ucId;
    }

    @Basic
    @Column(name = "UId")
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "CId")
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "InFocus")
    public int getInFocus() {
        return inFocus;
    }



    @Basic
    @Column(name = "startDate")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "cancellDate")
    public Timestamp getCancellDate() {
        return cancellDate;
    }

    public void setCancellDate(Timestamp cancellDate) {
        this.cancellDate = cancellDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsercommoditylogEntity that = (UsercommoditylogEntity) o;

        if (ucId != that.ucId) return false;
        if (uId != that.uId) return false;
        if (cId != that.cId) return false;
        if (inFocus != that.inFocus) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (cancellDate != null ? !cancellDate.equals(that.cancellDate) : that.cancellDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ucId;
        result = 31 * result + uId;
        result = 31 * result + cId;
        result = 31 * result + (int) inFocus;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (cancellDate != null ? cancellDate.hashCode() : 0);
        return result;
    }
}
