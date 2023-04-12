package com.boot.reserveproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noticeNo")
    private Long id;
    private String subject;
    @Column(length = 10000)
    private String context;
    private LocalDate date;

    @PrePersist
    public void setDate() {
        this.date = LocalDate.now();
    }

}
