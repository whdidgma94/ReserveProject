package com.boot.reserveproject.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberForm {
    @NotBlank
    private String loginId;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;
    @NotBlank
    private String zipcode;
    @NotBlank
    private String roadAddress;
    @NotBlank
    private String jibunAddress;
    @NotBlank
    private String detailAddress;
    @NotBlank
    private String regNum1;
    @NotBlank
    private String regNum2;
    @NotBlank
    private String emailId;
    @NotBlank
    private String emailDomain;
    @NotBlank
    private String phone;

}
