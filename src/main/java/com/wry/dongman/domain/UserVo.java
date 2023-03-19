package com.wry.dongman.domain;

import java.util.Date;

public class UserVo {
    private Long id;
    private String password;

    private String password2;
    private Integer type;

    private String email;

    private String name;

    private Integer sex;

    private Date ctime;

    private Date utime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                ", type=" + type +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
