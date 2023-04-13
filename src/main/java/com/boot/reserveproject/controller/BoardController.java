package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Board;
import com.boot.reserveproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/pc/board/boardList")
    private String showBoardList(Model model){
        List<Board>boardList=boardService.selectAllBoard();
        model.addAttribute("boardList",boardList);
        return"pc/board/boardList";
    }
    @GetMapping("/pc/board/addBoard")
    private String addBoard(Model model){
    Long length= boardService.getBoardLength();
    model.addAttribute("no",length);
        return"pc/board/addBoard";
    }
}
