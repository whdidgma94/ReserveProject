package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query("select c from Comments c where c.board.no= :no")
    List<Comments> findCommentsByBoardNo(@Param("no") long no);
}
