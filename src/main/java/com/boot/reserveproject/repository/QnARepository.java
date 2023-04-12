package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnARepository extends JpaRepository<QnA, Long> {
    @Query("select q from QnA q where q.receiver = :receiver")
    List<QnA> getMyQnA(@Param("receiver")String receiver);
}
