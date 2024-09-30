package com.example.hotel.service;

import com.example.hotel.entity.Room;

public interface RoomService {
    Room getRoomById(Long roomId);
    Room getRoomByName(String roomName); // 새로 추가하는 메서드
}
