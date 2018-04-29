package com.workspace.server.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by 邻家的小胖子 on 2018/4/29.
 */
@Entity
@Table(name = "userfeedback", schema = "shiro", catalog = "")
public class UserfeedbackEntity {
    private int ufId;
    private int uId;
    private String userContent;
    private Timestamp ufCreateTime;

    @Id
    @Column(name = "UFId")
    public int getUfId() {
        return ufId;
    }

    public void setUfId(int ufId) {
        this.ufId = ufId;
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
    @Column(name = "UserContent")
    public String getUserContent() {
        return userContent;
    }

    public void setUserContent(String userContent) {
        this.userContent = userContent;
    }

    @Basic
    @Column(name = "UFCreateTime")
    public Timestamp getUfCreateTime() {
        return ufCreateTime;
    }

    public void setUfCreateTime(Timestamp ufCreateTime) {
        this.ufCreateTime = ufCreateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserfeedbackEntity that = (UserfeedbackEntity) o;

        if (ufId != that.ufId) return false;
        if (uId != that.uId) return false;
        if (userContent != null ? !userContent.equals(that.userContent) : that.userContent != null) return false;
        if (ufCreateTime != null ? !ufCreateTime.equals(that.ufCreateTime) : that.ufCreateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ufId;
        result = 31 * result + uId;
        result = 31 * result + (userContent != null ? userContent.hashCode() : 0);
        result = 31 * result + (ufCreateTime != null ? ufCreateTime.hashCode() : 0);
        return result;
    }
}
