package com.example.restaurant.dao;

import com.example.restaurant.domain.Cart;
import com.example.restaurant.domain.CartVo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.util.List;

public interface CartDao {

    List<Cart> findCartsByUid(Integer u_id);

    void addCart(int u_id, int mid,Integer count);

    Cart findCartsByMidAndUid(int u_id, int mid);

    void updateCart(int u_id, int mid, Integer count);

    public List<CartVo> findAllChartByUid(Integer u_id);

    public List<CartVo> findAllChartByUidAndCids(Integer u_id,String cids);

    void deleteCartByCid(Integer cid);

    void deleteAllCartByUid(Integer u_id);

    CartVo findMenuByCid(Integer cid);

    void updateCartByCount(Integer count, Integer cid);

    void deleteCartByCidAndConnection(String cid);
}
