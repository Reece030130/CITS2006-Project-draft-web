package com.example.restaurant.service.impl;

import com.example.restaurant.dao.CartDao;
import com.example.restaurant.dao.impl.CartDaoImpl;
import com.example.restaurant.domain.Cart;
import com.example.restaurant.domain.CartVo;
import com.example.restaurant.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    CartDao cartDao = new CartDaoImpl();
    @Override
    public List<Cart> findCartsByUid(Integer u_id) {
        return cartDao.findCartsByUid(u_id);
    }

    @Override
    public void addCart(int u_id, int mid,Integer count) {
        cartDao.addCart(u_id,mid,count);
    }

    @Override
    public Cart findCartsByMidAndUid(int u_id, int mid) {
        return cartDao.findCartsByMidAndUid(u_id,mid);
    }

    @Override
    public void updateCart(int u_id, int mid, Integer count) {
        cartDao.updateCart(u_id,mid,count);

    }

    @Override
    public List<CartVo> findAllChartByUid(Integer u_id) {
        return cartDao.findAllChartByUid(u_id);
    }

    @Override
    public List<CartVo> findAllChartByUidAndCids(Integer u_id,String[] cids) {
        String cidStr="";
        for (int i = 0;i<cids.length;i++){
                if (i==cids.length-1){
                    cidStr=cidStr+cids[i];
                }else {
                    cidStr=cidStr+cids[i]+",";
                }
            }
            return cartDao.findAllChartByUidAndCids(u_id,cidStr);

    }

    @Override
    public void deleteCartByCid(Integer cid) {
        cartDao.deleteCartByCid(cid);
    }

    @Override
    public void deleteAllCartByUid(Integer u_id) {
        cartDao.deleteAllCartByUid(u_id);
    }

    @Override
    public CartVo findMenuByCid(Integer cid) {
        return cartDao.findMenuByCid(cid);
    }

    @Override
    public void updateCartByCount(Integer count, Integer cid) {
        cartDao.updateCartByCount(count,cid);
    }
}
