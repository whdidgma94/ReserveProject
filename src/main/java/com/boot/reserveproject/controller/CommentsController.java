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
        Long size= commentsService.countAllComments();
        if(size==0){
            System.out.println("댓글테이블길이:"+size);
            comments.setRef(1L);
        }
        else{

            Long newSize= commentsService.findMaxRef();
            newSize++;
            System.out.println("댓글그룹:"+newSize);
            comments.setRef(newSize);
        }
        comments.setId(id);
        comments.setContent(comment);

        comments.setLevel(1L);
        commentsService.createOrUpdateComments(comments);
        Board newBoard =boardService.findOneBoardByNo(no);
        model.addAttribute("board",newBoard);
        List<Comments> newComments=commentsService.getCommentsByBoardNo(no);
        model.addAttribute("comments",newComments);
        return"pc/board/showContent";
    }
    @PostMapping("/pc/comments/addSecondComment")
    private String addSecondComment(Model model,@RequestParam("id") String id,@RequestParam("no") Long no){
    Comments comment= commentsService.findOneCommentByBoardNo(no);
    model.addAttribute("comment",comment);
        return"pc/board/addSecondComment";
    }
    @PostMapping("/pc/comments/addSecondCommentPro")
    private String addSecondCommentPro(Model model,@RequestParam("id")String id,@RequestParam("content") String content,
                                       @RequestParam("ref") long ref, @RequestParam("level") long level, @RequestParam("boardNo") long boardNo){
        Comments comment =new Comments();
        comment.setId(id);
        comment.setContent(content);
        comment.setDepth(2L);
        comment.setRef(ref);
        Long maxLevel=commentsService.findMaxLevelByRef(ref);
        maxLevel++;
        comment.setLevel(maxLevel);
        Board board= boardService.getOneBoardByNo(boardNo);
        comment.setBoard(board);
        commentsService.createOrUpdateComments(comment);
        List<Comments> newComments=commentsService.getCommentsByBoardNo(boardNo);
        model.addAttribute("comments",newComments);
        Board newBoard =boardService.findOneBoardByNo(boardNo);
        model.addAttribute("board",newBoard);
        return "pc/board/showContent";
    }
    @PostMapping("/pc/comments/deleteComment")
    private void deleteComment(Model model,@RequestParam("no") long no){
        System.out.println("deleteComment");
        commentsService.deleteComment(no);
        Board board =boardService.findOneBoardByNo(no);
        model.addAttribute("board",board);
        List<Comments> newComments=commentsService.getCommentsByBoardNo(no);
        model.addAttribute("comments",newComments);

    }

}
