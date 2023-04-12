package com.boot.reserveproject.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NoticeForm {
    @NotBlank
    private String subject;
    private String context;
}
