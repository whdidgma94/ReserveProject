package com.boot.reserveproject.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class QnAForm {
    @NotBlank
    private String sender;
    @NotBlank
    private String receiver;
    private String category;
    private String context;
}
