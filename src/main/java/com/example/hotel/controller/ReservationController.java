package com.example.hotel.controller;

import com.example.hotel.entity.Room;
import com.example.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservation")
    public String reservation(
            // RequestParam은 html에서 보낸 데이터!
            @RequestParam("checkin-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkInDate,
            @RequestParam("checkout-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate  checkOutDate,
            Model model){
        List<Room> availableRooms = reservationService.findAvailableRooms(checkInDate, checkOutDate);
        System.out.println("Available rooms: " + availableRooms);
        model.addAttribute("availableRooms", availableRooms);
        return "reservation/reservation";
    }
}
