package com.boot.reserveproject.service;


import com.boot.reserveproject.domain.Board;
import com.boot.reserveproject.domain.Comments;
import com.boot.reserveproject.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    @Autowired
    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }
    public void createOrUpdateComments(Comments comments){
        commentsRepository.save(comments);
    }
    public List<Comments> getCommentsByBoardNo(Long no){
        return commentsRepository.findCommentsByBoardNo(no);
    }
}
