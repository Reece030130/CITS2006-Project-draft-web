package com.example.restaurant.service.impl;

import com.example.restaurant.dao.UserDao;
import com.example.restaurant.dao.impl.UserDaoImpl;
import com.example.restaurant.domain.User;
import com.example.restaurant.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public void register(User user) {
        userDao.register(user);
    }

    @Override
    public User findUsername(String userName) {
        return userDao.findUsername(userName);
    }
}
