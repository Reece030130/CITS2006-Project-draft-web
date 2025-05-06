package com.example.restaurant.domain;

public class OrdersDetail {
    private String odid;
    private Integer mid;
    private Integer odcount;
    private String oid;
    private double price;

    public String getOdid() {
        return odid;
    }

    public void setOdid(String odid) {
        this.odid = odid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getOdcount() {
        return odcount;
    }

    public void setOdcount(Integer odcount) {
        this.odcount = odcount;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrdersDetail{" +
                "odid='" + odid + '\'' +
                ", mid=" + mid +
                ", odcount=" + odcount +
                ", oid='" + oid + '\'' +
                ", price=" + price +
                '}';
    }

    public OrdersDetail(String odid, Integer mid, Integer odcount, String oid, double price) {
        this.odid = odid;
        this.mid = mid;
        this.odcount = odcount;
        this.oid = oid;
        this.price = price;
    }

    public OrdersDetail(){

    }
}
