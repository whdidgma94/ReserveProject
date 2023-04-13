package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Board;
import com.boot.reserveproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        Board board=new Board();
        board.setNo(no);
        board.setId(id);
        board.setTitle(title);
        board.setContent(content);

        board.setImg(img);

        boardService.createOrUpdateBoard(board);

        List<Board>boardList=boardService.selectAllBoard();
        model.addAttribute("boardList",boardList);
        return"pc/board/boardList";
    }
    @GetMapping("/pc/board/showContent")
    private String showContent(Model model,@RequestParam("no") long no){
        System.out.println("들어는 오나  "+no);
        Optional<Board> board =boardService.findOneBoardByNo(no);
        model.addAttribute("board",board);
        return "pc/board/showContent";
    }
    @GetMapping("/pc/board/deleteBoard")
    private String deleteBoard(Model model,@RequestParam("no") long no){
        System.out.println("여기들어와와오아?");
        boardService.deleteBoard(no);
        List<Board>boardList=boardService.selectAllBoard();
        model.addAttribute("boardList",boardList);
        return"pc/board/boardList";
    }
    @GetMapping("/pc/board/updateBoard")
    private String updateBoard(Model model,@RequestParam("no") long no){
        Optional<Board> board=boardService.findOneBoardByNo(no);
        model.addAttribute("board",board);
        return"pc/board/updateBoard";
    }
    @PostMapping("/pc/board/updateBoardPro")
    private String updateBoardPro(Model model,@RequestParam("id") String id,
                                  @RequestParam("no") long no,

                                  @RequestParam("title") String title,
                                  @RequestParam("content") String content,
                                  @RequestParam("img") String img){
        Optional<Board> board=boardService.findOneBoardByNo(no);
        Board newBoard=new Board();
        newBoard.setNo(board.get().getNo());
        newBoard.setId(board.get().getId());
        newBoard.setTitle(title);
        newBoard.setContent(content);
        newBoard.setReadCnt(board.get().getReadCnt());
        newBoard.setImg(img);
        newBoard.setDate(board.get().getDate());
        newBoard.setTime(board.get().getTime());
        boardService.createOrUpdateBoard(newBoard);
        board=boardService.findOneBoardByNo(no);
        model.addAttribute("board",board);
        return "pc/board/showContent";
    }
}
