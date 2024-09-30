package com.example.hotel.service;

import com.example.hotel.entity.User;

public interface UserService {
    public User findByUserEmail(String email);
}
