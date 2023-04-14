package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Member;
import com.boot.reserveproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public boolean validId(String loginId) {
        List<Member> members = memberRepository.selectAll(loginId);
        if (!members.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean checkLogin(String loginId, String pw) {
        Member member = memberRepository.checkLogin(loginId, pw);
        if (member != null) {
            return true;
        }
        return false;
    }

    public void removeMember(Long id) {
        memberRepository.deleteById(id);
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다"));
    }

    public boolean validPhoneNumber(String phone) {
        List<Member> members = memberRepository.selectPhoneNumber(phone);
        if (!members.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validEmail(String email) {
        List<Member> members = memberRepository.selectEmail(email);
        if (!members.isEmpty()) {
            return false;
        }
        return true;
    }
    public boolean validEmailAndloginId(String email, String loginId) {
        List<Member> members = memberRepository.selectEmailAndLoginId(email, loginId);
        if (!members.isEmpty()) {
            return false;
        }
        return true;
    }
    public String loginId(String type, String address){
        String loginId = null;
        if(type.equals("email")){
            loginId = memberRepository.selectEmail(address).get(0).getLoginId();
        }else{
            loginId = memberRepository.selectPhoneNumber(address).get(0).getLoginId();
        }
        return loginId;
    }
    @Modifying
    @Transactional
    public void updateTemp(String type, String address, String code) {
        if (type.equals("phone")) {
            memberRepository.updateTempByPhone(code, address);
        } else {
            memberRepository.updateTempByEmail(code, address);
        }
    }

    public Member selectMemberById(String loginId) {
        return memberRepository.selectMemberByLoginId(loginId);
    }
}