package com.example.restaurant.dao;

import com.example.restaurant.domain.User;

public interface UserDao {
    User login(User user);

    void register(User user);

    User findUsername(String userName);
}
