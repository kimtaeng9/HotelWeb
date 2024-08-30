package com.example.hotel.service;

import com.example.hotel.dto.ReservationForm;
import com.example.hotel.entity.Reservation;
import com.example.hotel.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    // 모든 예약을 조회
    List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate);

    // 새로운 예약을 생성
    Reservation makeReservation(ReservationForm reservationForm);


}
