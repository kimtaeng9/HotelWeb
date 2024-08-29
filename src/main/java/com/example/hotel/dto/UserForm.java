package com.example.hotel.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserForm {
    private Long id;

    private String username;
    private String email;
    private String phone;
    private String role;
    private String password;
    private LocalDate createdAt;
}
