package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Member;
import com.boot.reserveproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final MemberRepository memberRepository;

    public List<Member> allMembers() {
        List<Member> list = memberRepository.findAll();
        if (list.isEmpty()) throw new IllegalStateException("데이터가 존재하지않습니다");
        return list;
    }

    public void removeMember(Long id) {
        memberRepository.deleteById(id);
    }

    public Member oneMember(Long id) {

        return memberRepository.findById(id).get();
    }

}
