package com.example.hotel.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
public class MainController {

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

    @GetMapping("/")
    public String showMainPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            model.addAttribute("username", "Guest");
            model.addAttribute("isGuest", true);
        } else {
            model.addAttribute("username", username);
            model.addAttribute("isGuest", false);
        }
        return "main";
    }

    @GetMapping("/contact")
    public String showContactPage(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return "contact/contact";
    }

/*    @GetMapping("/reservation")
    public String showReservationPage() {
        return "reservation/reservation";
    }*/
}