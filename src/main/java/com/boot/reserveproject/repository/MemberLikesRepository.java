package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.domain.Member;
import com.boot.reserveproject.domain.MemberLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberLikesRepository extends JpaRepository<MemberLikes, Long> {
    @Modifying
    @Query("delete from MemberLikes ml where ml.camp.contentId = :contentId and ml.member.id = :id")
    void deleteMemberLikesByInfo(@Param("contentId") Long contentId, @Param("id") Long id);

    @Query("select ml from MemberLikes ml where ml.camp.contentId = :contentId and ml.member.id = :id")
    MemberLikes selectMemberLikesByInfo(@Param("contentId") Long contentId, @Param("id") Long id);
    @Query("select ml.camp.contentId from MemberLikes  ml where ml.member.loginId = :loginId")
    List<Long> selectMemberListByLoginId(@Param("loginId") String loginId);

    @Modifying
    @Query("delete from MemberLikes ml where ml.member.loginId = :loginId")
    void deleteMemberLikesByLoginId(@Param("loginId") String loginId);
}
