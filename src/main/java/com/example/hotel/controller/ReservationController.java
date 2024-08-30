package com.example.hotel.controller;

import com.example.hotel.dto.ReservationForm;
import com.example.hotel.entity.Room;
import com.example.hotel.entity.User;
import com.example.hotel.service.ReservationService;
import com.example.hotel.service.SignService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private SignService signService;

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

    @GetMapping("/reservation/search")
    public String reservation() {
        return "reservation/search";
    }
    // 날짜로 예약가능한 방조회
    @GetMapping("/reservation")
    public String reservation(
            // RequestParam은 html에서 보낸 데이터!
            @RequestParam("checkin-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkInDate,
            @RequestParam("checkout-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate  checkOutDate,
            @RequestParam("adults") String adults,
           // @RequestParam("children") String children,
            Model model){
        List<Room> availableRooms = reservationService.findAvailableRooms(checkInDate, checkOutDate);
        System.out.println("Available rooms: " + availableRooms);
        System.out.println("checkInDate: " + checkInDate);
        model.addAttribute("availableRooms", availableRooms);
        model.addAttribute("checkInDate", checkInDate);
        model.addAttribute("checkOutDate", checkOutDate);
        model.addAttribute("adults", adults);
       // model.addAttribute("children", children);
        return "reservation/reservation";
    }


   @GetMapping("/reservation/detail")
   public String showReservationDetail(@RequestParam("roomType") String roomType,
                                       @RequestParam("roomName") String roomName,
                                       @RequestParam("price") String price,
                                       Model model,
                                       HttpSession session,
                                       RedirectAttributes redirectAttributes) {
       // 로그인 상태 확인
       String username = (String) session.getAttribute("username");

       if (username == null || username.equals("Guest")) {
           return "redirect:/sign/signin";
       }
       // 사용자 정보 가져오기
       User user = signService.findUserByUsername(username);
       if (user != null) {
           model.addAttribute("email", user.getEmail());
           model.addAttribute("phone", user.getPhone());
       }
       System.out.println("roomType : " + roomType);
       model.addAttribute("roomType", roomType);
       model.addAttribute("roomName", roomName);
       model.addAttribute("price", price);
       return "reservation/detail";
   }
   // 예약 확정 페이지
   @PostMapping("/reservation/confirm")
    public String confirmReservation(@ModelAttribute ReservationForm reservationForm,
                                     HttpSession session){
        reservationService.makeReservation(reservationForm);
        return "reservation/confirm";
   }

}
