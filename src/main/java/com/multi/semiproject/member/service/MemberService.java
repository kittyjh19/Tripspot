package com.multi.semiproject.member.service;

import com.multi.semiproject.member.model.dao.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;


    //테스트용
    public int memberTest() {
        return memberMapper.memberTest();
    }
}
