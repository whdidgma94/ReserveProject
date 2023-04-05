package com.boot.reserveproject.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class MemberForm {
    @NotBlank(message = "ID는 필수 입력 항목입니다.")
    private String loginId;
    @NotBlank(message = "PW는 필수 입력 항목입니다.")
    private String pw;
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;
    @NotBlank(message = "주소는 필수 입력 항목입니다.")
    private String address;
    @NotBlank(message = "주민등록번호는 필수 입력 항목입니다.")
    private String regNum;
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email
    private String email;
    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    private String phone;

}
