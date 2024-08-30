package com.example.hotel.service;

import com.example.hotel.dto.UserForm;
import com.example.hotel.entity.User;
import com.example.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService{
    @Autowired // db와 상호작용
    private UserRepository userRepository;

    public User signUp(UserForm userForm) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setUsername(userForm.getUsername());
        user.setPhone(userForm.getPhone());
        user.setPassword(userForm.getPassword());
        user.setRole("USER"); // 기본 역할 설정
        return userRepository.save(user);
    }


    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User signIn(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}