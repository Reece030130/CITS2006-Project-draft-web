package com.example.restaurant.domain;

import  java.util.Date;
import java.util.List;

public class Orders {
 private  String oid;
 private Date ordertime;
 private Integer uid;
 private String uremark;
 private String state;
 private double total;
 private Integer count;
 private List<OrdersDetail> ordersDetails;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUremark() {
        return uremark;
    }

    public void setUremark(String uremark) {
        this.uremark = uremark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<OrdersDetail> getOrdersDetails() {
        return ordersDetails;
    }

    public void setOrdersDetails(List<OrdersDetail> ordersDetails) {
        this.ordersDetails = ordersDetails;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid='" + oid + '\'' +
                ", ordertime=" + ordertime +
                ", uid=" + uid +
                ", uremark='" + uremark + '\'' +
                ", state='" + state + '\'' +
                ", total=" + total +
                ", count=" + count +
                ", ordersDetails=" + ordersDetails +
                '}';
    }

    public Orders(String oid, Integer uid, String uremark, String state, double total) {
        this.oid = oid;
        this.uid = uid;
        this.uremark = uremark;
        this.state = state;
        this.total = total;
    }
}
