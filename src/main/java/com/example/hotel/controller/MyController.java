package com.example.hotel.controller;

import com.example.hotel.entity.Reservation;
import com.example.hotel.service.ReservationService;
import org.springframework.ui.Model;
import com.example.hotel.entity.User;
import com.example.hotel.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class MyController {

    @ModelAttribute
    public void addCommonAttributes(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            model.addAttribute("username", "Guest");
            model.addAttribute("isGuest", true);
            model.addAttribute("isAdmin", false);
        } else if (username.equals("admin")) {
            model.addAttribute("username", username);
            model.addAttribute("isGuest", false);
            model.addAttribute("isAdmin", true);
        } else {
            model.addAttribute("username", username);
            model.addAttribute("isGuest", false);
            model.addAttribute("isAdmin", false);
        }
    }

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
/*
        if(email == null) {
            return "redirect:/sign/signin";
        }
*/
        User user = userService.findByUserEmail(email);
        model.addAttribute("user", user);
       // model.addAttribute("username", user.getUsername()); // username 필드를 모델에 추가
        return "/user/myPage";
    }

    @GetMapping("/myReservations")
    public String myReservation(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        User user = userService.findByUserEmail(email);
        List<Reservation> reservations = reservationService.getReservationsByUser(user.getId());
        model.addAttribute("reservations", reservations);
        return "/user/myReservation";
    }

}
