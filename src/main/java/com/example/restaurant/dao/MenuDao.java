package com.example.restaurant.dao;

import com.example.restaurant.domain.Menu;

import java.util.List;

public interface MenuDao {
    public List<Menu> findAllMenu();
    public Menu findMenuDetail(int mid);
    public int findMenuCount(int ftno,int startPrice,int endPrice);
    public int addMenu(Menu menu);
    public int deleteMenu(int mid);
    public int updateMenu(Menu menu);
    List<Menu> findMenuByName(String menuName);
    List<Menu> limitedMenuList(Integer limit,Integer offset,int ftno,int startPrice,int endPrice);
}
