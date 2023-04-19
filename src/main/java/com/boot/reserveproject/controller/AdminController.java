package com.boot.reserveproject.controller;

import com.boot.reserveproject.domain.Board;
import com.boot.reserveproject.domain.BoardWithCommentsCount;
import com.boot.reserveproject.domain.Comments;
import com.boot.reserveproject.domain.Member;

import com.boot.reserveproject.service.AdminService;
import com.boot.reserveproject.service.BoardService;
import com.boot.reserveproject.service.CommentsService;
import com.boot.reserveproject.service.CampService;
import com.boot.reserveproject.service.MemberService;
import com.boot.reserveproject.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final BoardService boardService;
    private final CommentsService commentsService;

    private final QnAService qnAService;
    private final MemberService memberService;
    private final CampService campService;

    @GetMapping("/admin/main")
    public String adminPage(Model model) {
        return "admin/index";
    }
    @GetMapping("/admin/memberList")
    public String getMembers(Model model) {
        model.addAttribute("memberList", adminService.allMembers());
        return "/admin/member/memberList";
    }

    @PostMapping("/admin/deleteMember")
    public String deleteMember(@RequestParam("id") Long id) {
        Member member = memberService.findMember(id);
        String loginId = member.getLoginId();
        qnAService.deleteBySender(loginId);
        campService.deleteMemberLikesByLoginId(loginId);
        adminService.removeMember(id);
        return "/admin/member/memberList";
    }

    @GetMapping("/admin/memberInfo")
    public ResponseEntity<Member> getMemberInfo(@RequestParam("id") Long id) {
        Member member = adminService.oneMember(id);
        return ResponseEntity.ok(member);
    }
    @GetMapping("/admin/board/boardList")
    public String boardList(Model model){
        List<BoardWithCommentsCount> boardList=boardService.findBoardWithCommentsCountByNo();
        model.addAttribute("boardList",boardList);
        return "/admin/board/boardList";
    }
    @GetMapping("/admin/board/showContent")
    private String showContent(Model model,@RequestParam("no") long no){
        System.out.println("no:"+no);
        Board board =boardService.findOneBoardByNo(no);
        Long readCnt=board.getReadCnt();
        readCnt++;
        System.out.println("조회수:"+readCnt);
        board.setReadCnt(readCnt);
        boardService.createOrUpdateBoard(board);
        model.addAttribute("board",board);
        List<Comments> newComments=commentsService.getCommentsByBoardNo(no);
        model.addAttribute("comments",newComments);
        return "/admin/board/showContent";
    }
    @PostMapping("/admin/board/deleteComment")
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
        return "/admin/board/showContent";
    }

    @GetMapping("/admin/board/deleteBoard")
    private String deleteBoard(Model model,@RequestParam("no") long no){

        boardService.deleteBoard(no);
//        List<Board>boardList=boardService.selectAllBoard();
        List<BoardWithCommentsCount>boardList=boardService.findBoardWithCommentsCountByNo();
        model.addAttribute("boardList",boardList);

        return"admin/board/boardList";
    }

}
