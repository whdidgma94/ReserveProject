package com.boot.reserveproject.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateForm {
    private String pw;
    private String postcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String phone;
    private String emailId;
    private String emailDomain;
}
