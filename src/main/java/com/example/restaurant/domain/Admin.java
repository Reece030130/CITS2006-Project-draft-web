package com.example.restaurant.domain;

public class Admin {
    public String aname;
    public String apwd;
    public String aemail;
    public String aphone;

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApwd() {
        return apwd;
    }

    public void setApwd(String apwd) {
        this.apwd = apwd;
    }

    public String getAemail() {
        return aemail;
    }

    public void setAemail(String aemail) {
        this.aemail = aemail;
    }

    public String getAphone() {
        return aphone;
    }

    public void setAphone(String aphone) {
        this.aphone = aphone;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aname='" + aname + '\'' +
                ", apwd='" + apwd + '\'' +
                ", aemail='" + aemail + '\'' +
                ", aphone='" + aphone + '\'' +
                '}';
    }
}
