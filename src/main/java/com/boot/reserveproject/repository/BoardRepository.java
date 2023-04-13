package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
@Query("select b from Board b")
    List<Board>selectAllBoard();

    @Query("SELECT COUNT(b) FROM Board b")
    Long countBoards();

}
