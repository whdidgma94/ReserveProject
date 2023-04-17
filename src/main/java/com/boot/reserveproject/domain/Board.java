package com.boot.reserveproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Long no;
    private String id;
    private String title;//게시글제목
    @Column(length = 10000)
    private String content;//게시글내용
    @Column(name = "readCnt", columnDefinition = "bigint default 0")
    private Long readCnt;//조회수
    private String img;//등록이미지
    private LocalDate date;//등록날짜
    private String time;//등록시간
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();
    @PrePersist
    public void setPrePersist() {
        this.date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.time=time.format(formatter);
        if (this.readCnt == null) {
            this.readCnt = 0L;
        }

    }

}
