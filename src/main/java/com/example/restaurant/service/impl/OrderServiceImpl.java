package com.example.restaurant.service.impl;

import com.example.restaurant.dao.CartDao;
import com.example.restaurant.dao.OrdersDao;
import com.example.restaurant.dao.OrdersDetailDao;
import com.example.restaurant.dao.impl.CartDaoImpl;
import com.example.restaurant.dao.impl.OrdersDaoImpl;
import com.example.restaurant.dao.impl.OrdersDetailDaoImpl;
import com.example.restaurant.domain.CartVo;
import com.example.restaurant.domain.Orders;
import com.example.restaurant.domain.OrdersDetail;
import com.example.restaurant.service.OrderService;
import com.example.restaurant.util.JDBCUtils;
import org.springframework.jdbc.core.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl extends JdbcTemplate implements OrderService {
    OrdersDao ordersDao = new OrdersDaoImpl();
    OrdersDetailDao ordersDetailDao = new OrdersDetailDaoImpl();
    CartDao cartDao = new CartDaoImpl();

    @Override
    public void confirmOrders(List<CartVo> cartVos, Orders orders, String cid) throws SQLException {
        try {
            JDBCUtils.beginTransaction();
            ordersDao.addOrders(orders);
            for (CartVo cartVo : cartVos) {
                String o_did = UUID.randomUUID().toString();
                OrdersDetail orderDetail= new OrdersDetail(o_did,cartVo.getMid(),cartVo.getCount(),orders.getOid(),cartVo.getMprice()*cartVo.getCount());
                ordersDetailDao.addOrderDetail(orderDetail);
            }
            cartDao.deleteCartByCidAndConnection(cid);
            JDBCUtils.commit();
        }catch (SQLException e){
            JDBCUtils.rollback();
        }finally {
            JDBCUtils.getConnection().close();
        }
    }

    @Override
    public List<Orders> findOrderListsByUid(Integer uid) {
        List<OrdersDetail> ordersDetails = new ArrayList<OrdersDetail>();
        List<Orders> orders = ordersDao.findOrderListsByUid(uid);
        for (Orders order : orders) {
            ordersDetails=ordersDetailDao.findOrdersDetailByOid(order.getOid());
            order.setOrdersDetails(ordersDetails);
        }
        return orders;
    }
}
//    public static void main(String[] args) {
//        JdbcTemplate jdbcTemplate = new OrderServiceImpl();
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        String sql = "aaa";
//        jdbcTemplate.execute(sql);
//        orderService.execute(sql);
//    }
//
//    public void execute(final String sql) throws DataAccessException {
//        System.out.println(sql);
//    }