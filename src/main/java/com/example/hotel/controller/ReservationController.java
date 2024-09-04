package com.example.hotel.controller;

import com.example.hotel.dto.ReservationForm;
import com.example.hotel.dto.UserForm;
import com.example.hotel.entity.Room;
import com.example.hotel.entity.User;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.service.ReservationService;
import com.example.hotel.service.RoomService;
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

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private SignService signService;
    @Autowired
    private RoomService roomService;


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
            HttpSession session,
            Model model){
        String username = (String) session.getAttribute("username");
        User user = signService.findUserByUsername(username);

        session.setAttribute("checkin-date", checkInDate);
        session.setAttribute("checkout-date", checkOutDate);

        List<Room> availableRooms = reservationService.findAvailableRooms(checkInDate, checkOutDate);


        model.addAttribute("availableRooms", availableRooms);
        model.addAttribute("checkInDate", checkInDate);
        model.addAttribute("checkOutDate", checkOutDate);
        model.addAttribute("adults", adults);
        return "reservation/reservation";
    }


    @GetMapping("/reservation/detail")
    public String showReservationDetail(@RequestParam("roomType") String roomType,
                                        @RequestParam("roomName") String roomName,
                                        @RequestParam("price") String price,
                                        @RequestParam("adults") String adults,
                                      //  @RequestParam("roomId") Long roomId,
                                      //  @RequestParam("userId") Long userId,
                                        Model model,
                                        HttpSession session) {

            String username = (String) session.getAttribute("username");
            if (username == null || username.equals("Guest")) {
                return "redirect:/sign/signin";
            }

            User user = signService.findUserByUsername(username);
            if (user != null) {
                model.addAttribute("email", user.getEmail());
                model.addAttribute("phone", user.getPhone());
                model.addAttribute("userId", user.getId());
            }

             LocalDate checkInDate = (LocalDate) session.getAttribute("checkin-date");
             LocalDate checkOutDate = (LocalDate) session.getAttribute("checkout-date");

        Room room = roomService.getRoomByName(roomName);

            model.addAttribute("roomType", roomType);
            model.addAttribute("roomName", roomName);
            model.addAttribute("price", price);
            model.addAttribute("checkInDate", checkInDate);
            model.addAttribute("checkOutDate", checkOutDate);
            model.addAttribute("adults", adults);
            model.addAttribute("roomId", room.getRoomId());
            return "reservation/detail";

    }
    @PostMapping("/reservation/confirm")
    public String confirmReservation(@ModelAttribute ReservationForm reservationForm,
                                     @ModelAttribute UserForm userForm,
                                     Model model,
                                     HttpSession session) {
        System.out.println("예약확인");
        System.out.println("Received ReservationForm: " + reservationForm);
        System.out.println("Received UserForm: " + userForm);
        System.out.println("Received ReservationForm.getRoomId(): " + reservationForm.getRoomId());


        // 현재 세션에서 사용자 정보를 가져옵니다.
        String username = (String) session.getAttribute("username");
        if (username != null) {
            reservationService.getUserIdFromResv(username, reservationForm);
            // Assume getUserIdFromResv sets the userId in reservationForm based on the session's username
        } else {
            // Handle case where user is not logged in
            throw new IllegalStateException("User is not logged in or session is invalid");
        }


        // 방 정보를 처리하고 예약 생성
        reservationService.makeReservation(reservationForm);
        return "reservation/confirm";
    }



}
