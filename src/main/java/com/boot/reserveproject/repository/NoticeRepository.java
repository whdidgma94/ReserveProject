package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Modifying
    @Query("update Notice n set n.subject=:subject, n.context=:context where n.id=:id")
    void updateNotice(@Param("subject") String subject, @Param("context") String context, @Param("id") Long id);
}
