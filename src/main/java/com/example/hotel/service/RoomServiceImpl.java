package com.example.hotel.service;

import com.example.hotel.entity.Room;
import com.example.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    @Override
    public Room getRoomByName(String roomName) {
        return roomRepository.findByRoomName(roomName);
    }

    @Override // 총가격
    public Long getTotalPrice(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        Room room = roomRepository.findById(roomId).orElse(null);
        long totalDays = DAYS.between(checkInDate,checkOutDate);
        Long totalPrice = room.getPrice() * totalDays;
        if(totalDays > 7) {
            totalPrice = (long)(totalPrice * 0.9);
        }
        return totalPrice;
    }
}
