package com.example.restaurant.dao.impl;

import com.example.restaurant.dao.MenuDao;
import com.example.restaurant.domain.Menu;
import com.example.restaurant.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class MenuDaoImpl  implements MenuDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Menu> findAllMenu() {
        String sql = "select*from menu";
        List<Menu> menus = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Menu>(Menu.class));
        return  menus;}


    @Override
    public Menu findMenuDetail(int mid) {
        String sql = "select * from menu where mid =?";
        Menu menu = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Menu>(Menu.class),mid);
        return menu;
    }

    @Override
    public int findMenuCount(int ftno,int startPrice,int endPrice) {
       String sql = "Select count(*)from menu where ftno=? and mprice between ? and ?";
       return jdbcTemplate.queryForObject(sql,int.class,ftno,startPrice,endPrice);
    }

    @Override
    public int addMenu(Menu menu) {
        String sql = "insert into menu values(null,?,?,?,?,?)";
       return jdbcTemplate.update(sql,menu.getMname(),menu.getMprice(),menu.getMintroduce(),menu.getMimg(),menu.getFtno());
    }

    @Override
    public int deleteMenu(int mid) {
        String sql ="delete from menu where mid=?";
        return jdbcTemplate.update(sql,mid);
    }

    @Override
    public int updateMenu(Menu menu) {
        String sql ="update user set mname=?,mprice=?,mintroduce=?,mimg=?,ftno=?";
        return jdbcTemplate.update(sql,menu.getMname(),menu.getMprice(),menu.getMintroduce(),menu.getMimg(),menu.getFtno());
    }

    @Override
    public List<Menu> findMenuByName(String menuName) {
        String sql ="select * from menu where mname like '%"+menuName+"%'";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Menu>(Menu.class));
    }

    @Override
    public List<Menu> limitedMenuList(Integer limit,Integer offset,int ftno,int startPrice,int endPrice) {
        String sql = "select *from menu where where ftno=? and mprice between ? and ? limit "+limit+" offset "+offset;
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Menu>(Menu.class),ftno,startPrice,endPrice);
    }

}
