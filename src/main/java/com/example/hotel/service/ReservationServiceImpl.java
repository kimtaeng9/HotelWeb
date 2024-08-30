package com.example.hotel.service;

import com.example.hotel.dto.ReservationForm;
import com.example.hotel.entity.Reservation;
import com.example.hotel.entity.Room;
import com.example.hotel.repository.ReservationRepository;
import com.example.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired // db와 상호작용
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override // 모든 예약 조회
    public List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> rooms = reservationRepository.findAvailableRooms(checkInDate, checkOutDate);
        System.out.println("Rooms from repository: " + rooms);
        return rooms;
    }

    @Override // 새로운 예약 생성
    public Reservation makeReservation(ReservationForm reservationForm){
        Reservation reservation = new Reservation();
        reservation.setUserId(reservationForm.getUserId());
        reservation.setRoomId(reservationForm.getRoomId());
        reservation.setCheckInDate(reservationForm.getCheckInDate());
        reservation.setCheckOutDate(reservationForm.getCheckOutDate());
        reservation.setStatus("reserved");
        reservation.setCreatedAt(LocalDate.now());
        System.out.println("qqqq"+reservation.getId());
        // 예약된 방의 상태 업데이트
        Room room = roomRepository.findById(reservationForm.getRoomId()).orElse(null);
        room.setStatus("reserved");
        roomRepository.save(room);
        return reservationRepository.save(reservation);
    }


}
