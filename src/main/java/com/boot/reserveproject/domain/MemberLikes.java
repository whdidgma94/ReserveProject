package com.boot.reserveproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class MemberLikes {
    private Long memberId;
    private String loginId;
    private Long campId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberLikesId")
    private Long id;

    public static MemberLikes createMemberLikes(Member member, Camp camp) {
        MemberLikes memberLikes = new MemberLikes();
        memberLikes.setMemberId(member.getId());
        memberLikes.setCampId(camp.getContentId());
        memberLikes.setLoginId(member.getLoginId());
        return memberLikes;
    }
}
