package com.boot.reserveproject.domain;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
@Getter
@Setter
public class BoardWithCommentsCount {
    private Long no;
    private String id;
    private String title;//게시글제목

    private String content;//게시글내용
    private Long readCnt;//조회수
    private String img;//등록이미지
    private LocalDate date;//등록날짜
    private String time;//등록시간
    private Long commentsCount;
    private Member member;

    public BoardWithCommentsCount(Long no, String id, String title, String content, Long readCnt, String img, LocalDate date, String time, Long commentsCount) {
        this.no = no;
        this.id = id;
        this.title = title;
        this.content = content;
        this.readCnt = readCnt;
        this.img = img;
        this.date = date;
        this.time = time;
        this.commentsCount = commentsCount;

    }
}
