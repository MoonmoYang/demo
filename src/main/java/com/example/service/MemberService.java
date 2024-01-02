package com.example.service;

import com.example.domain.Member;
import com.example.repository.MemberRepository;
import com.example.repository.MemoryMemberRepository;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getMb_name())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public String join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMb_name();
    }

}
