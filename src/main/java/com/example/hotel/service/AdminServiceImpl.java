package com.example.hotel.service;

import com.example.hotel.entity.Reservation;
import com.example.hotel.entity.User;
import com.example.hotel.repository.ReservationRepository;
import com.example.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public User getUserInfo(String email) { // 기존 List<User>에서 User로 변경
        return userRepository.findByEmail(email); // 변경된 메서드 사용
    }
}

