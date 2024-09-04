package com.example.hotel.service;

import com.example.hotel.entity.Reservation;
import com.example.hotel.entity.User;

import java.util.List;

public interface AdminService {
    List<Reservation> getAllReservations();

    User getUserInfo(String email);
}
