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
    void getUserIdFromResv(String username, ReservationForm reservationForm);
    List<Reservation> getReservationsByUser(Long userId); // 이메일로 예약자 찾기
    void deleteReservation(Long reservationId);
}
