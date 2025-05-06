package com.example.restaurant.service;

import com.example.restaurant.domain.CartVo;
import com.example.restaurant.domain.Orders;
import com.example.restaurant.domain.OrdersDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    public void confirmOrders(List<CartVo> cartVos, Orders orders, String cid) throws SQLException;

    List<Orders> findOrderListsByUid(Integer uid);
}
