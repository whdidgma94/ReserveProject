package com.boot.reserveproject.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Long no;

    private String id;
    @Column(length = 10000)
    private String content;
    private Long ref;
    @Column(name = "depth", columnDefinition = "bigint default 1")
    private Long depth;
    private Long level;
    private LocalDate date;//등록날짜
    private String time;//등록시간
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @PrePersist
    public void setPrePersist() {
        if(this.depth==null){
            this.depth=1L;
        }
        this.date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.time=time.format(formatter);


    }
}
