package com.example.restaurant.domain;

import java.io.Serializable;

public class CartVo implements Serializable {
    private  Integer cid;
    private double mprice;
    private  String mname;
    private Integer count;
    private Integer mid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public double getMprice() {
        return mprice;
    }

    public void setMprice(double mprice) {
        this.mprice = mprice;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "cid=" + cid +
                ", mprice=" + mprice +
                ", mname='" + mname + '\'' +
                ", count=" + count +
                ", mid=" + mid +
                '}';
    }
}
