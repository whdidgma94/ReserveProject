package com.boot.reserveproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MemberLikes {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo")
    private Member member;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contentId")
    private Camp camp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberLikesId")
    private Long id;

    public static MemberLikes createMemberLikes(Member member, Camp camp) {
        MemberLikes memberLikes = new MemberLikes();
        memberLikes.setMember(member);
        memberLikes.setCamp(camp);
        return memberLikes;
    }
}
