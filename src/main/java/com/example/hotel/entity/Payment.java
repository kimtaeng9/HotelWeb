package com.example.hotel.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
@Table(name="payment")
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_id;

    @OneToOne
    @JoinColumn(name="reservation_id")
    private Reservation reservation;

    private String payment_method;
    private String payment_status;
    private Date created_at;
}
