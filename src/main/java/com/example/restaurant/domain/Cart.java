package com.example.restaurant.domain;

public class Cart {
    private Integer cid;
    private Integer mid;
    private Integer uid;
    private Integer count;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", mid=" + mid +
                ", uid=" + uid +
                ", count=" + count +
                '}';
    }
}
