package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Member;
import com.boot.reserveproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long join(Member member) {
        validMemberId(member.getLoginId());
        memberRepository.save(member);
        return member.getId();
    }

    private void validMemberId(String loginId) {
        List<Member> members = memberRepository.selectAll(loginId);
        if (!members.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원아이디입니다");
        }
    }

    public List<Member> findAllMembers() {
        List<Member> list = memberRepository.findAll();
        if (list.isEmpty()) throw new IllegalStateException("데이터가 존재하지않습니다");
        return list;
    }

    @Transactional
    public void removeMember(Long id) {
        memberRepository.deleteById(id);
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다"));
    }
    public boolean validPhoneNumber(String phone){
        List<Member> members = memberRepository.selectPhoneNumber(phone);
        if (!members.isEmpty()) {
            return false;
        }
        return true;
    }
}