package com.example.hotel.service;

import com.example.hotel.entity.Room;
import com.example.hotel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        // 데이터 조회가 정상적으로 이루어지는지 확인
        List<Room> rooms = reservationRepository.findAvailableRooms(checkInDate, checkOutDate);
        System.out.println("Rooms from repository: " + rooms);
        return rooms;
    }
}
