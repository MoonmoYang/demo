package com.example.service;

import com.example.domain.Member;
import com.example.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Optional;


@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveMember(Member member) {
        memberRepository.save(member);
    }




    private void validateDuplicateMember(Member member) {
        memberRepository.findByLoginId(member.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<Member> findByLoginId(String loginId){
        return memberRepository.findByLoginId(loginId);
    }


    // 회원가입 메서드
    @Transactional
    public Member join(Member member){
        if (member.getMbPw() == null || member.getMbPw().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 비어 있을 수 없습니다.");
        }
        validateDuplicateMember(member);
        String encodedPassword = passwordEncoder.encode(member.getMbPw());
        member.setMbPw(encodedPassword);
        return memberRepository.save(member);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    // 회원 정보 수정 (Update)
    @Transactional
    public Member updateMemberByLoginId(String loginId, Member updatedMemberInfo) {
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);
        if (memberOptional.isPresent()) {
            Member foundMember = memberOptional.get();
            foundMember.setMbName(updatedMemberInfo.getMbName());
            foundMember.setEmail(updatedMemberInfo.getEmail());
            String encodedPassword = passwordEncoder.encode(updatedMemberInfo.getMbPw());
            foundMember.setMbPw(encodedPassword);
            foundMember.setMbGrade(updatedMemberInfo.getMbGrade());

            return memberRepository.save(foundMember);
        } else {
            throw new RuntimeException("Member not found with loginId: " + loginId);
        }
    }




    public Optional<Member> signInForm(String loginId, String password){
        Optional<Member> foundMember = findByLoginId(loginId);
        if(foundMember.isPresent()) {
            Member member = foundMember.get();
            if(passwordEncoder.matches(password, member.getMbPw())) {
                return foundMember;
            }
        }
        return Optional.empty();
    }
    // 회원 리스트
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public boolean checkPassword(String mbPw, String mbPw1) {
        return passwordEncoder.matches(mbPw, mbPw1);
    }

    public boolean checkForDuplicateId(String id) {
        return memberRepository.findByLoginId(id).isPresent();
    }

    public Member signin(String loginId, String mbPw) {
        Optional<Member> foundMember = findByLoginId(loginId);
        if(foundMember.isPresent()) {
            Member member = foundMember.get();
            if(passwordEncoder.matches(mbPw, member.getMbPw())) {
                return member;
            }
        }
        throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    // 회원 탈퇴
    @Transactional
    public void deleteMemberByLoginId(String loginId) {
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);
        if (memberOptional.isPresent()) {
            memberRepository.delete(memberOptional.get());
        } else {
            throw new RuntimeException("Member not found with loginId: " + loginId);
        }
    }


    public boolean doesLoginIdExist(String loginId) {
        return memberRepository.findByLoginId(loginId).isPresent();
    }
}









