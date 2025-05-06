package com.example.restaurant.dao;

import com.example.restaurant.domain.Orders;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface OrdersDao {

    public void addOrders(Orders orders) ;

    List<Orders> findOrderListsByUid(Integer uid);
}
