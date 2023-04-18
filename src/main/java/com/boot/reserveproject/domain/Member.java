package com.boot.reserveproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private String loginId;
    private String pw;
    private String name;
    private String postcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String regNum1;
    private String regNum2;
    private String email;
    private String phone;
    private String gender;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Camp> campLikes;

    @JsonIgnore
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();


}
