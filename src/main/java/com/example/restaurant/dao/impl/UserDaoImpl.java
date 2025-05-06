package com.example.restaurant.dao.impl;

import com.example.restaurant.dao.UserDao;
import com.example.restaurant.domain.User;
import com.example.restaurant.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDaoImpl implements UserDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User login(User user) {
        String sql = "select * from users where u_name=? and u_pwd=?";
        try {
          return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),user.getU_name(),user.getU_pwd());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void register(User user) {
        String sql ="insert into users values(null,?,?,?,null,null,?,?)";
        jdbcTemplate.update(sql,user.getU_name(),user.getU_pwd(),user.getU_sex(),user.getU_phone(),user.getU_email());
    }

    @Override
    public User findUsername(String userName) {
        String sql = "select * from users where u_name=?";
        try {
            return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),userName);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
