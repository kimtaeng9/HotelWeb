package com.example.hotel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long roomId;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;
    private LocalDate createdAt;

}
