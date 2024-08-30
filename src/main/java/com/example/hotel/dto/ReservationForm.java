package com.example.hotel.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class ReservationForm {
    private Long id;
    private Long userId;
    private Long roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;
    private LocalDateTime createdAt;
}
