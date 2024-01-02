package com.example.repository;

import com.example.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(String id);

    Optional<Member> findByName(String name);

    List<Member> findAll();


}
