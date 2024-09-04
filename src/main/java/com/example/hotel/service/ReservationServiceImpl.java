package com.example.hotel.service;

import com.example.hotel.dto.ReservationForm;
import com.example.hotel.entity.Reservation;
import com.example.hotel.entity.Room;
import com.example.hotel.entity.User;
import com.example.hotel.repository.ReservationRepository;
import com.example.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired // db와 상호작용
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private SignService signService;

    @Override // 모든 예약 조회
    public List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> rooms = reservationRepository.findAvailableRooms(checkInDate, checkOutDate);
        System.out.println("(Service)Rooms from repository: " + rooms);
        return rooms;
    }

    public void getUserIdFromResv(String username, ReservationForm reservationForm) {
        User user = signService.findUserByUsername(username);
        if (user != null) {
            reservationForm.setUserId(user.getId()); // 사용자 ID 설정
        }
    }

    @Override // 새로운 예약 생성
    @Transactional
    public Reservation makeReservation(ReservationForm reservationForm){
        // 1. 예약 객체 생성 및 설정
        Reservation reservation = new Reservation();
        User user = signService.findUserById(reservationForm.getUserId());
        Room room = roomRepository.findById(reservationForm.getRoomId()).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found for the given ID: " + reservationForm.getUserId());
        }
        reservation.setUser(user);
        reservation.setRoom(room);

        // reservation.setUserId(reservationForm.getUserId());
        System.out.println("(Service)reservationForm.getUserId()"+reservationForm.getUserId());
      //  reservation.setRoomId(reservationForm.getRoomId());
        System.out.println("(Service)reservationForm.getRoomId())"+reservationForm.getRoomId());
        reservation.setCheckInDate(reservationForm.getCheckInDate());
        System.out.println("(Service)reservationForm.getCheckInDate())"+reservationForm.getCheckInDate());
        reservation.setCheckOutDate(reservationForm.getCheckOutDate());
        reservation.setAdults(reservationForm.getAdults());
        System.out.println("(Service)reservationForm.getAdults())"+reservationForm.getAdults());
       // reservation.setRoomId(reservationForm.getRoomId());
        System.out.println("(Service)reservationForm.getRoomId())"+reservationForm.getRoomId());
        reservation.setStatus("confirmed"); // confirm , pending, cancel
        reservation.setCreatedAt(LocalDate.now());


        // 3. 예약을 db에 저장
        Reservation savedReservation = reservationRepository.save(reservation);
        System.out.println("(Service)Reservation Id: " + savedReservation.getId());
        return savedReservation;
    }
}
