package com.example.hotel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private String userId;
    private String roomId;

    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private String status;
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "reservation")
    private Payment payment;
}
