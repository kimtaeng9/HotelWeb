package com.example.hotel.dto;

import lombok.Data;

@Data
public class UserForm {
    private Long id;
    private String password;
    private String username;
    private String email;
    private String phone;
}
