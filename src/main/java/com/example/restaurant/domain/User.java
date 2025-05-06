package com.example.restaurant.domain;

import java.util.Date;

public class User {
    private Integer uid;
    private String u_name;
    private String u_pwd;
    private Integer u_sex;
    private Integer u_age;
    private String u_address;
    private String u_phone;
    private String u_email;

    public String getU_name() {
        return u_name;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_pwd() {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

    public Integer getU_sex() {
        return u_sex;
    }

    public void setU_sex(Integer u_sex) {
        this.u_sex = u_sex;
    }

    public Integer getU_age() {
        return u_age;
    }

    public void setU_age(Integer u_age) {
        this.u_age = u_age;
    }

    public String getU_address() {
        return u_address;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id=" + uid +
                ", u_name='" + u_name + '\'' +
                ", u_pwd='" + u_pwd + '\'' +
                ", u_sex=" + u_sex +
                ", u_age=" + u_age +
                ", u_address='" + u_address + '\'' +
                ", u_phone='" + u_phone + '\'' +
                ", u_email='" + u_email + '\'' +
                '}';
    }
}


