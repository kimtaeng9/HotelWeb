package com.example.hotel.service;

import com.example.hotel.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate);
}
