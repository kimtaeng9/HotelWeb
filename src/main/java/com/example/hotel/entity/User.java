package com.example.hotel.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="users")
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String username;
    private String email;
    private String phone;
    private String role;
    public boolean isAdmin() {
        return this.role.equals("ROLE_ADMIN");
    }
}
