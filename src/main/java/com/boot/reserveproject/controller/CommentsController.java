package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Board;
import com.boot.reserveproject.domain.Comments;
import com.boot.reserveproject.service.BoardService;
import com.boot.reserveproject.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommentsController {
    private final BoardService boardService;
    private final CommentsService commentsService;
    @PostMapping("/pc/comments/addComment")
    private String addComment(Model model, @RequestParam("loginId") String id,@RequestParam("comment") String comment,
                              @RequestParam("no") Long no){
        Comments comments = new Comments();
        Board board= boardService.getOneBoard(no);
        comments.setBoard(board);

        comments.setId(id);
        comments.setContent(comment);
        comments.setRef(1L);
        comments.setLevel(1L);
        commentsService.createOrUpdateComments(comments);
        Optional<Board> newBoard =boardService.findOneBoardByNo(no);
        model.addAttribute("board",newBoard);
        List<Comments> newComments=commentsService.getCommentsByBoardNo(no);
        model.addAttribute("comments",newComments);
        return"pc/board/showContent";
    }
}
