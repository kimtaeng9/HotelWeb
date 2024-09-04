package com.example.hotel.controller;

import com.example.hotel.dto.UserForm;
import com.example.hotel.entity.User;
import com.example.hotel.service.SignService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignController {

    @Autowired
    private SignService signService;

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

    @GetMapping("/sign/signin")
    public String getSignin() {
        return "/sign/signin"; // 메인 페이지로 연결될 경우
    }
    @GetMapping("/sign/signup")
    public String getSignup() {
        return "/sign/signup"; // 메인 페이지로 연결될 경우
    }
    @PostMapping("/signup")
    public String postSignup(@ModelAttribute UserForm userForm) {
        signService.signUp(userForm);
        return "redirect:/sign/signin";
    }

    @PostMapping("/signin")
    public String postSignin(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpSession session, Model model) {
        User user = signService.signIn(email, password);
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("isGuest", false); // 로그인 상태 설정
            return "redirect:/";  // 로그인 후 메인 페이지로 이동
        } else {
            model.addAttribute("loginError", "Invalid email or password");
            return "/sign/signin";
        }
    }
    @GetMapping("/signout")
    public String logout(HttpSession session) {
        // 세션에서 모든 사용자 정보 제거
        session.invalidate(); // 세션 무효화

        return "redirect:/";
    }


}