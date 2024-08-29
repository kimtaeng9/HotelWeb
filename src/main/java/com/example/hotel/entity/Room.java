package com.example.hotel.entity;

import jakarta.persistence.*;
import lombok.*;
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
    private Long roomId;

    private String roomType;
    private String roomName;
    private Long price;


    private String status;


}
