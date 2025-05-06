package com.example.restaurant.dao;

import com.example.restaurant.domain.OrdersDetail;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.util.List;

public interface OrdersDetailDao {

    public void addOrderDetail(OrdersDetail ordersDetail);

    List<OrdersDetail> findOrdersDetailByOid(String oid);
}
