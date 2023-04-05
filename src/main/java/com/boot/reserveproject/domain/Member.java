package com.boot.reserveproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberNo")
    private Long id;
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
    private String gender;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();
}
