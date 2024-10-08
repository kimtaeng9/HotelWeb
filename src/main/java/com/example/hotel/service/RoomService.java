package com.example.hotel.service;

import com.example.hotel.entity.Room;

import java.time.LocalDate;

public interface RoomService {
    Room getRoomById(Long roomId);
    Room getRoomByName(String roomName); // 새로 추가하는 메서드
    Long getTotalPrice(Long roomId,LocalDate checkInDate, LocalDate checkOutDate); // 총 가격
}
