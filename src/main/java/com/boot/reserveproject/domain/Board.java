package com.boot.reserveproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "board")
public class Board {
    @Id
    @Column(name = "no")
    private Long no;//게시글번호
    private String id;//작성자
    private String title;//게시글제목
    @Column(length = 10000)
    private String content;//게시글내용
    private String readCnt;//조회수
    private String img;//등록이미지
    private LocalDate date;//등록날짜
    private LocalDateTime time;//등록시간
    private Long ref;//글 그룹
    private Long reStep;//글 깊이(단계)
    private Long reLevel;//같은 글 그룹의 순서(정렬)
    @PrePersist
    public void setPrePersist() {
        this.date = LocalDate.now();
        this.time = LocalDateTime.now();
    }

}
