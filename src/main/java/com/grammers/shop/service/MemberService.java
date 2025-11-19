package com.grammers.shop.service;

import com.grammers.shop.common.ResponseEntity;
import com.grammers.shop.member.Member;
import com.grammers.shop.member.MemberRepository;
import com.grammers.shop.member.MemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public ResponseEntity<List<Member>> findAll(){
        return new ResponseEntity<>(HttpStatus.OK.value(), memberRepository.findAll(), memberRepository.count());
    }

    public ResponseEntity<Member> create(MemberRequest request){
        Member member = new Member(
                UUID.randomUUID(),
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
        Member member1 = memberRepository.save(member);
        int cnt = 0;
        if(member1 instanceof List){
            cnt = ((List<?>) member1).size();
        }else{
            cnt=1;
        }
        return new ResponseEntity<>(HttpStatus.OK.value(), member1, cnt);
    }

    public ResponseEntity<Member> update(MemberRequest request, String id){
        Member member = new Member(
                id,
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
        Member member1 = memberRepository.save(member);
        int cnt = 0;
        if(member1 instanceof List){
            cnt = ((List<?>) member1).size();
        }else{
            cnt=1;
        }
        return new ResponseEntity<>(HttpStatus.OK.value(), member1, cnt);
    }

    public ResponseEntity<?> delete(String id){
        memberRepository.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK.value(), null, 1);
    }
}
