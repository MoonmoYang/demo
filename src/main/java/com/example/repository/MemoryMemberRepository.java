package com.example.repository;

import com.example.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<Long, Member>();
    private static Long sequence = 0L;


    @Override
    public Member save(Member member){
        member.setSequence(++sequence);
        store.put(member.getMb_id(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(String id){
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public Optional<Member> findByName(String name){
        return store.values().stream()
                .filter(member -> member.getMb_name().equals(member.getMb_name()))
                .findAny();
    }
    @Override
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }



}
