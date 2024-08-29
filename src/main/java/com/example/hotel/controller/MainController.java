package com.example.hotel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {
    @GetMapping("/")
    public String showHomePage() {
        return "main"; // 메인 페이지로 연결될 경우
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact/contact";
    }

/*    @GetMapping("/reservation")
    public String showReservationPage() {
        return "reservation/reservation";
    }*/
}