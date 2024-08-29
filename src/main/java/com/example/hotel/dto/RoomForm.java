package com.example.hotel.dto;

import lombok.Data;
@Data
public class RoomForm {
    private Long roomId;

    private String roomType;
    private String roomName;
    private Long price;


    private String status;
}
