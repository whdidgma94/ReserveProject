package com.boot.reserveproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QnANo")
    private Long id;
    private String sender;
    private String receiver;
    private String category;
    @Column(length = 10000)
    private String context;
    private LocalDate date;
    private String status;
    @PrePersist
    public void setDate() {
        this.date = LocalDate.now();
    }
}
