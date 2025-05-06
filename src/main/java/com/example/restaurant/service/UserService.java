package com.example.restaurant.service;

import com.example.restaurant.domain.User;

public interface UserService {
    User login(User user);

    void register(User user);

    User findUsername(String userName);
}
