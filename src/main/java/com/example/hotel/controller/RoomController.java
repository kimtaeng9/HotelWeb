package com.example.hotel.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RoomController {

    @ModelAttribute
    public void addCommonAttributes(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            model.addAttribute("username", "Guest");
            model.addAttribute("isGuest", true);
        } else {
            model.addAttribute("username", username);
            model.addAttribute("isGuest", false);
        }
    }


    @GetMapping("/room/deluxe")
    public String showDeluxeRoom(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return "room/deluxe";
    }

    @GetMapping("/room/executive")
    public String showExecutiveRoom(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return "room/executive";
    }

    @GetMapping("/room/presidential")
    public String showPresidentialRoom(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return "room/presidential";
    }
}