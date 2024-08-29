package com.example.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignController {
    @GetMapping("/sign/signin")
    public String getSignin() {
        return "/sign/signin"; // 메인 페이지로 연결될 경우
    }
    @GetMapping("/sign/signup")
    public String getSignup() {
        return "/sign/signup"; // 메인 페이지로 연결될 경우
    }

}
