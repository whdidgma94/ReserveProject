package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnARepository extends JpaRepository<QnA, Long> {
    @Query("select q from QnA q where q.sender = :sender")
    List<QnA> getMyQnA(@Param("sender") String sender);

    @Query("select q from QnA  q where q.connectedId = :id")
    QnA getAnswer(@Param("id") Long id);

    @Query("select q from QnA q where q.sender != 'admin'")
    List<QnA> getQuestionList();

    @Query("select q.sender from QnA q where q.id = :id")
    String getSender(@Param("id") Long id);

    @Modifying
    @Query("update QnA q set q.status = 'Read' where q.id = :id")
    void updateStatusRead(@Param("id") Long id);

    @Modifying
    @Query("update QnA q set q.status = 'Done' where q.id = :id")
    void updateStatusDone(@Param("id") Long id);

    @Modifying
    @Query("delete from QnA q where q.sender = :sender")
    void deleteBySender(@Param("sender") String sender);


}
