package com.multi.semiproject.member.model.dao;


import com.multi.semiproject.member.model.dto.MemberDTO;

public interface MemberMapper {

    MemberDTO findMemberById(String memberId);

    int registMember(MemberDTO memberDTO);
}
