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

import static java.time.temporal.ChronoUnit.DAYS;
import java.time.LocalDate;
import java.util.List;
@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private SignService signService;

    @Override
    public List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> rooms = reservationRepository.findAvailableRooms(checkInDate, checkOutDate);

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
        Reservation reservation = new Reservation();
        User user = signService.findUserById(reservationForm.getUserId());
        Room room = roomRepository.findById(reservationForm.getRoomId()).orElse(null);
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(reservationForm.getCheckInDate());
        reservation.setCheckOutDate(reservationForm.getCheckOutDate());
        reservation.setAdults(reservationForm.getAdults());
        reservation.setStatus("confirmed"); // confirm , pending, cancel
        reservation.setCreatedAt(LocalDate.now());

        Long totalPrice = roomService.getTotalPrice(reservation.getRoom().getRoomId(), reservationForm.getCheckInDate(), reservationForm.getCheckOutDate());
        reservation.setTotalPrice(totalPrice);
        return reservationRepository.save(reservation);
    }

    @Override // 해당 유저PK로 예약목록
    public List<Reservation> getReservationsByUser(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    @Override // 예약삭제
    public void deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
