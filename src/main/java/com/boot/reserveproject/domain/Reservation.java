package com.boot.reserveproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservationNo")
    private Long no;
    @NotBlank(message = "예약 날짜는 필수 입력 항목입니다.")
    private LocalDate reserveDate;
    @NotBlank(message = "예약 일수는 필수 입력 항목입니다.")
    private Long reserveDay;
    private Long price;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Camp camp;

}

