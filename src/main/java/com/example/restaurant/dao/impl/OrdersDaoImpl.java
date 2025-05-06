package com.example.restaurant.dao.impl;

import com.example.restaurant.dao.OrdersDao;
import com.example.restaurant.domain.Orders;
import com.example.restaurant.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public void addOrders(Orders orders)  {
        Date date= new Date();
        PreparedStatement preparedStatement = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd hh:mm:ss");
        dateFormat.format(date);
        Timestamp sqlDate = new Timestamp(date.getTime());
        String sql = "insert into orders (oid,ordertime,uid,uremark,state,total) values(?,?,?,?,?,?)";
        try {
            Connection connection = JDBCUtils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,orders.getOid());
            preparedStatement.setTimestamp(2, sqlDate);
            preparedStatement.setInt(3,orders.getUid());
            preparedStatement.setString(4,orders.getUremark());
            preparedStatement.setString(5,orders.getState());
            preparedStatement.setDouble(6,orders.getTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Orders> findOrderListsByUid(Integer uid) {
        String sql = "select * from orders  where uid=?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Orders>(Orders.class),uid);
    }
}
