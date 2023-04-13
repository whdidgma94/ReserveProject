package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Board;
import com.boot.reserveproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        System.out.println("length:"+length);
    if(length==0){
        model.addAttribute("no",1);
    }
    else{
        Optional<Board> board= boardService.getLastBoard();
        Long no=board.get().getNo();
        no++;
        model.addAttribute("no",no);
    }
        return"pc/board/addBoard";
    }

    @PostMapping("/pc/board/addBoardPro")

    private String addBoardPro(@RequestParam("id") String id,
                               @RequestParam("no") long no,

                               @RequestParam("title") String title,
                               @RequestParam("content") String content,
                               @RequestParam("img") String img,
                               Model model){

        System.out.println(id+"  "+no+"  "+title+"  "+content+"  "+img);
        boardService.insertBoard(id,no,title,content,img);
        List<Board>boardList=boardService.selectAllBoard();
        model.addAttribute("boardList",boardList);
        return"pc/board/boardList";
    }
}
