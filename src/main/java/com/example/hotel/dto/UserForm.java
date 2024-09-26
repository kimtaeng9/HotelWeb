package com.example.hotel.dto;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {
    private Long id;

    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

    @Pattern(regexp = "", message = "이름은 한글만 입력 가능합니다.")
    private String username;

    @Pattern(regexp =  "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$" , message = "이메일 형식이 유효하지 않습니다.")
    private String email;

    @Pattern(regexp = "010-\\d{3,4}-\\d{4}", message = "전화번호 형식이 유효하지 않습니다. (예: 010-1234-5678)")
    private String phone;
}
