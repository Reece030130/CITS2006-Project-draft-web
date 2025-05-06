package com.example.restaurant.service.impl;

import com.example.restaurant.dao.MenuDao;
import com.example.restaurant.dao.impl.MenuDaoImpl;
import com.example.restaurant.domain.Menu;
import com.example.restaurant.service.MenuService;

import java.util.List;

public class MenuServiceImpl implements MenuService {
    MenuDao menuDao = new MenuDaoImpl();

    @Override
    public List<Menu> findAllMenu() {
        return menuDao.findAllMenu();
    }

    @Override
    public Menu findMenuDetail(int mid) {
        return menuDao.findMenuDetail(mid);
    }

    @Override
    public int findMenuCount(int ftno,int startPrice,int endPrice) {
        return menuDao.findMenuCount(ftno,startPrice,endPrice);
    }

    @Override
    public int addMenu(Menu menu) {
        return menuDao.addMenu(menu);
    }

    @Override
    public int deleteMenu(int mid) {
        return menuDao.deleteMenu(mid);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuDao.updateMenu(menu);
    }

    @Override
    public List<Menu> findMenuByName(String menuName) {
        return  menuDao.findMenuByName(menuName);
    }

    @Override
    public List<Menu> limitedMenuList(Integer limit,Integer offset,int ftno,int startPrice,int endPrice) {
        return menuDao.limitedMenuList(limit,offset,ftno,startPrice,endPrice);
    }

}
