package com.workspace.server.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "shiro", catalog = "")
public class UsersEntity {
    private int uid;
    @Pattern(regexp="^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message="{UsersEntity.uNumber.space}")
    private String uNumber;
    private String uname;
    @Size(min=6,max=20,message="{UsersEntity.uPassWord.size}")
    private String uPassWord;

    @Id
    @Column(name = "UID")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "UNumber")
    public String getuNumber() {
        return uNumber;
    }

    public void setuNumber(String uNumber) {
        this.uNumber = uNumber;
    }

    @Basic
    @Column(name = "UNAME")
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Basic
    @Column(name = "UPassWord")
    public String getuPassWord() {
        return uPassWord;
    }

    public void setuPassWord(String uPassWord) {
        this.uPassWord = uPassWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return uid == that.uid &&
                Objects.equals(uNumber, that.uNumber) &&
                Objects.equals(uname, that.uname) &&
                Objects.equals(uPassWord, that.uPassWord);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid, uNumber, uname, uPassWord);
    }
}
