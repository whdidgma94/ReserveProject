package com.boot.reserveproject.repository;

import com.boot.reserveproject.domain.Camp;
import com.boot.reserveproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.loginId = :loginId")
    List<Member> selectAll(@Param("loginId") String loginId);

    @Query("select m from Member m where m.phone = :phone")
    List<Member> selectPhoneNumber(@Param("phone") String phone);

    @Query("select m from Member m where m.email = :email")
    List<Member> selectEmail(@Param("email") String email);

    @Query("select m from Member m where m.email = :email and m.loginId = :loginId")
    List<Member> selectEmailAndLoginId(@Param("email") String email, @Param("loginId") String loginId);

    @Query("select m from Member m where m.loginId = :loginId and m.pw = :pw")
    Member checkLogin(@Param("loginId") String loginId, @Param("pw") String pw);

    @Query("select m from Member m where m.loginId = :loginId")
    Member selectMemberByLoginId(@Param("loginId") String loginId);

    @Modifying
    @Query("update Member m set m.pw = :code where m.phone = :phone")
    void updateTempByPhone(@Param("code") String code, @Param("phone") String phone);

    @Modifying
    @Query("update Member m set m.pw = :code where m.email = :email")
    void updateTempByEmail(@Param("code") String code, @Param("email") String email);

    @Modifying
    @Query("update Member m set m.campLikes = :campLikes where m.id=:id")
    void updateMemberLikes(@Param("campLikes") List<Camp> campLikes, @Param("id") Long id);
}
