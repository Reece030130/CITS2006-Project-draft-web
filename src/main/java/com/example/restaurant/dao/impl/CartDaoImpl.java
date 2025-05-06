package com.example.restaurant.dao.impl;

import com.example.restaurant.dao.CartDao;
import com.example.restaurant.domain.Cart;
import com.example.restaurant.domain.CartVo;
import com.example.restaurant.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CartDaoImpl implements CartDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Cart> findCartsByUid(Integer u_id) {
        String sql = "select*from cart where uid=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Cart>(Cart.class), u_id);
    }

    @Override
    public void addCart(int u_id, int mid, Integer count) {
        String sql = "insert into cart values(null,?,?,?)";
        jdbcTemplate.update(sql, mid, u_id, count);
    }

    @Override
    public Cart findCartsByMidAndUid(int u_id, int mid) {
        String sql = "select*from cart where uid=? and mid=?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Cart>(Cart.class), u_id, mid);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateCart(int u_id, int mid, Integer count) {
        String sql = "update cart set count=? where uid=? and mid=?";
        jdbcTemplate.update(sql, count, u_id, mid);
    }

    @Override
    public List<CartVo> findAllChartByUid(Integer u_id) {
        String sql = "select menu.*,cart.*  FROM cart inner JOIN menu on cart.mid=menu.mid where uid=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<CartVo>(CartVo.class), u_id);
    }

    public List<CartVo> findAllChartByUidAndCids(Integer u_id, String cids) {
        String sql = "select menu.*,cart.*  FROM cart inner JOIN menu on cart.mid=menu.mid where uid=?";
        String sql1 = " and cid in (" + cids + ")";
        sql = sql + sql1;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<CartVo>(CartVo.class), u_id);

    }


    @Override
    public void deleteCartByCid(Integer cid) {
        String sql = "delete from cart where cid=?";
        jdbcTemplate.update(sql, cid);
    }

    @Override
    public void deleteAllCartByUid(Integer u_id) {
        String sql = "delete from cart where uid=?";
        jdbcTemplate.update(sql, u_id);
    }

    @Override
    public CartVo findMenuByCid(Integer cid) {
        String sql = "select menu.*,cart.*  FROM cart inner JOIN menu on cart.mid =menu.mid where cid=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<CartVo>(CartVo.class), cid);
    }

    @Override
    public void updateCartByCount(Integer count, Integer cid) {
        String sql = "update cart set count=? where cid=?";
        try {
            jdbcTemplate.update(sql, count, cid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCartByCidAndConnection(String cid) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String sql = "delete from cart where cid in "+cid;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
