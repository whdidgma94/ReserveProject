package com.boot.reserveproject.controller;

import com.boot.reserveproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
}
