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

/*    // 로그인한 사용자 정보 조회
    @GetMapping("/user")
    public ResponseEntity user(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(userDetails.getUsername(), HttpStatus.OK);
    }


    @GetMapping("/api1")
    public ResponseEntity api1(){
        return new ResponseEntity("api1",HttpStatus.OK);
    }

    @GetMapping("/api2")
    public ResponseEntity api2(){
        return new ResponseEntity("api1",HttpStatus.OK);
    }*/

    @GetMapping("/")
    public String showMainPage(HttpSession session, Model model) {
       /* String username = (String) session.getAttribute("username");
        if (username == null) {
            model.addAttribute("username", "Guest");
            model.addAttribute("isGuest", true);
        } else {
            model.addAttribute("username", username);
            model.addAttribute("isGuest", false);
        }*/
        return "main";
    }

    @GetMapping("/contact")
    public String showContactPage(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return "contact/contact";
    }
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
/*    @GetMapping("/reservation")
    public String showReservationPage() {
        return "reservation/reservation";
    }*/
}