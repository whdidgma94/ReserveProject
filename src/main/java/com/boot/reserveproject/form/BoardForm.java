package com.boot.reserveproject.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BoardForm {
    @NotBlank
    private Long no;
    @NotBlank
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String img;

}
