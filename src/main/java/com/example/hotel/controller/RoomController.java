package com.example.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomController {




    @GetMapping("/room/deluxe")
    public String showDeluxeRoom() {
        return "room/deluxe";
    }

    @GetMapping("/room/executive")
    public String showExecutiveRoom() {
        return "room/executive";
    }

    @GetMapping("/room/presidential")
    public String showPresidentialRoom() {
        return "room/presidential";
    }
}