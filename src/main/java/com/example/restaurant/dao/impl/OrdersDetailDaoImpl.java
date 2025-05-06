package com.example.restaurant.dao.impl;

import com.example.restaurant.dao.OrdersDetailDao;
import com.example.restaurant.domain.OrdersDetail;
import com.example.restaurant.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class OrdersDetailDaoImpl implements OrdersDetailDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public void addOrderDetail(OrdersDetail ordersDetail) {
        PreparedStatement preparedStatement = null;
        String sql = "insert into orders_detail(odid,mid,odcount,oid,price) values(?,?,?,?,?)";
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,ordersDetail.getOdid());
            preparedStatement.setInt(2, ordersDetail.getMid());
            preparedStatement.setInt(3,ordersDetail.getOdcount());
            preparedStatement.setString(4,ordersDetail.getOid());
            preparedStatement.setDouble(5, ordersDetail.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<OrdersDetail> findOrdersDetailByOid(String oid) {
        String sql = "select * from orders_detail where oid =?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<OrdersDetail>(OrdersDetail.class),oid);
    }
}
