package com.example.hotel.controller;

import com.example.hotel.entity.Reservation;
import com.example.hotel.entity.User;
import com.example.hotel.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;


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

    @GetMapping("/showReservList")
    public String showCReservList(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null || !username.equals("admin")) {
            return "redirect:/accessDenied";
        }
        List<Reservation> reservations = adminService.getAllReservations();
        model.addAttribute("reservations", reservations);

        return "admin/showReservList";
    }


    @GetMapping("/showUserInfo")
    public String showUserInfo(HttpSession session, Model model, @RequestParam String email) {
        String username = (String) session.getAttribute("username");
        if (username == null || !username.equals("admin")) {
            return "redirect:/accessDenied"; // Redirect to an access denied page
        }
        User userInfo = adminService.getUserInfo(email); // 단일 User 객체로 변경
        model.addAttribute("userInfo", userInfo); // 단일 객체로 모델에 추가

        return "admin/showUserInfo"; // 적절한 뷰 이름으로 변경
    }




}
