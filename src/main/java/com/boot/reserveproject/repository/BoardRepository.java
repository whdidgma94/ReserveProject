package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.Board;
import com.boot.reserveproject.domain.BoardWithCommentsCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
@Query("select b from Board b")
    List<Board>selectAllBoard();

    @Query("SELECT COUNT(b) FROM Board b")
    Long countBoards();
    @Query("SELECT b FROM Board b WHERE b.no = (SELECT MAX(b2.no) FROM Board b2)")
    Optional<Board> findBoardByMaxNo();
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO board (id, no, title, content, img) VALUES (:id, :no, :title, :content, :img)", nativeQuery = true)
    void insertBoard(@Param("id") String id, @Param("no") long no, @Param("title") String title, @Param("content") String content, @Param("img") String img);
    @Query("SELECT b FROM Board b WHERE b.no = :no")
    Board  findOneBoardByNo(@Param("no") long no);
    @Query("select b from Board b where b.no=:no")
    Board getOneBoardByNo(@Param("no") long no);
    @Query("SELECT new com.boot.reserveproject.domain.BoardWithCommentsCount(b.no, b.id, b.title, b.content, b.readCnt, b.img, b.date, b.time, COUNT(c)) " +
            "FROM Board b LEFT JOIN b.comments c ON b.no = c.board.no " +
            "GROUP BY b.no")
    List<BoardWithCommentsCount> findBoardWithCommentsCountByNo();
    @Modifying
    @Query("delete from Board b where b.id=:loginId")
    void deleteBoardByLoginId(@Param("loginId") String loginId);


}
