package com.workspace.server.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "administrator", schema = "shiro", catalog = "")
public class AdministratorEntity {
    private int aid;
    private String aNumber;
    private String aPassword;

    @Id
    @Column(name = "AID", nullable = false)
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "ANumber", nullable = false, length = 20)
    public String getaNumber() {
        return aNumber;
    }

    public void setaNumber(String aNumber) {
        this.aNumber = aNumber;
    }

    @Basic
    @Column(name = "APassword", nullable = false, length = 20)
    public String getaPassword() {
        return aPassword;
    }

    public void setaPassword(String aPassword) {
        this.aPassword = aPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministratorEntity that = (AdministratorEntity) o;
        return aid == that.aid &&
                Objects.equals(aNumber, that.aNumber) &&
                Objects.equals(aPassword, that.aPassword);
    }

    @Override
    public int hashCode() {

        return Objects.hash(aid, aNumber, aPassword);
    }
}
