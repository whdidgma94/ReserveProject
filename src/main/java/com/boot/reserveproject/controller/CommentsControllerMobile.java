package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Board;
import com.boot.reserveproject.domain.Comments;
import com.boot.reserveproject.service.BoardService;
import com.boot.reserveproject.service.CommentsService;
import lombok.RequiredArgsConstructor;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@Controller
@RequiredArgsConstructor
public class CommentsControllerMobile {
    private final BoardService boardService;
    private final CommentsService commentsService;

    @PostMapping("/mobile/comments/addComment")
    private String addComment(Model model, @RequestParam("loginId") String id,@RequestParam("comment") String comment,
                              @RequestParam("no") Long no){
        System.out.println("들어오나");
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
        return"mobile/board/showContent";
    }
    @PostMapping("/mobile/comments/addSecondComment")
    private String addSecondComment(Model model,@RequestParam("id") String id,@RequestParam("no") Long no){
        Comments comment= commentsService.findOneCommentByBoardNo(no);
        model.addAttribute("comment",comment);
        return"mobile/board/addSecondComment";
    }
    @PostMapping("/mobile/comments/addSecondCommentPro")
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
        return "mobile/board/showContent";
    }


    @PostMapping("/mobile/comments/deleteComment")

    private String deleteComment(Model model,@RequestParam("no") long no){
        Comments comment=commentsService.getOneCommentByCommentNo(no);
        System.out.println("댓글번호:"+no);


        System.out.println("깊이:  "+comment.getDepth());
        Long num = no;

        if(comment.getDepth()==1){
            long ref=comment.getRef();

            commentsService.deleteCommentsBySameRef(ref);

        }
        else{


            commentsService.deleteComment(no);
        }
        long boardNo=comment.getBoard().getNo();
        Board board=boardService.getOneBoardByNo(boardNo);
        model.addAttribute("board",board);
        List<Comments> newComments=commentsService.getCommentsByBoardNo(boardNo);
        model.addAttribute("comments",newComments);



        return "mobile/board/showContent";
    }
    @PostMapping("/mobile/comments/updateComment")


    private String updateComment(Model model,@RequestParam("no") long no){

        System.out.println("updateComment");
        Comments comment=commentsService.getOneCommentByCommentNo(no);
        model.addAttribute("comment",comment);
        return "mobile/board/updateComment";



    }
    @PostMapping("/mobile/comments/updateCommentPro")
    private String updateCommentPro(Model model,@RequestParam("commentNo") long no,@RequestParam("content") String content){
        Comments comment=commentsService.findOneCommentByBoardNo(no);
        comment.setContent(content);
        commentsService.createOrUpdateComments(comment);
        Board board=boardService.getOneBoardByNo(comment.getBoard().getNo());
        model.addAttribute("board",board);
        List<Comments> comments=commentsService.getCommentsByBoardNo(comment.getBoard().getNo());
        model.addAttribute("comments",comments);
        return "mobile/board/showContent";
    }


}

