package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.Board;
import com.boot.reserveproject.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query("select c from Comments c where c.board.no = :no order by c.ref asc, c.depth asc, c.level asc")
    List<Comments> findCommentsByBoardNo(@Param("no") long no);
    @Query("select count(*) from Comments")
    Long countAllComments();
    @Query(value = "SELECT MAX(ref) FROM Comments")
    Long findMaxRef();
    @Query("select c from Comments c where c.no= :no")
    Comments findOneCommentByBoardNo(@Param("no") long no);
    @Query(value="select MAX(c.level) from Comments c where c.ref= :ref")
    Long findMaxLevelByRef(@Param("ref") long ref);
    @Query("select count(*) from Comments c where c.board.no = :no")
    Long countCommentsByBoardNo(@Param("no") long no);
    @Query("select c from Comments c where c.no= :no")
    Comments getOneCommentByCommentNo(@Param("no") long no);
    @Modifying
    @Transactional
    @Query("delete from Comments c where c.ref= :ref")
    void deleteCommentsBySameRef(@Param("ref") long ref);
    @Modifying
    @Transactional
    @Query("delete from Comments c where c.id= :loginId")
    void deleteCommentsByLoginId(@Param("loginId") String loginId);

    }
