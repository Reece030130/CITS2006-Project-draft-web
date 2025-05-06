package com.example.restaurant.domain;

import java.io.Serializable;

public class Menu implements Serializable {
    private String mname;
    private double mprice;
    private String mintroduce;
    private String mimg;
    private Integer ftno;
    private Integer mid;

    public String getMname() {
        return mname;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public double getMprice() {
        return mprice;
    }

    public void setMprice(double mprice) {
        this.mprice = mprice;
    }

    public String getMintroduce() {
        return mintroduce;
    }

    public void setMintroduce(String mintriduce) {
        this.mintroduce = mintriduce;
    }

    public String getMimg() {
        return mimg;
    }

    public void setMimg(String mimg) {
        this.mimg = mimg;
    }

    public Integer getFtno() {
        return ftno;
    }

    public void setFtno(Integer ftno) {
        this.ftno = ftno;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "mname='" + mname + '\'' +
                ", mprice=" + mprice +
                ", mintroduce='" + mintroduce + '\'' +
                ", mimg='" + mimg + '\'' +
                ", ftno=" + ftno +
                ", mid=" + mid +
                '}';
    }
}
