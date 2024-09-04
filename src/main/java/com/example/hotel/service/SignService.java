package com.example.hotel.service;

import com.example.hotel.dto.UserForm;
import com.example.hotel.entity.User;


public interface SignService {
    User findUserByUsername(String username);
    User signUp(UserForm userForm);
    User signIn(String email, String password);

    User findUserById(Long userId);
}
