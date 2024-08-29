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
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long room_id;

    private String room_type;
    private String room_name;
    private Long price;

    @ManyToOne
    @JoinColumn(name="reserved_user_id")
    private User reservedUser;
    private String status;
    private LocalDateTime created_at;


}
