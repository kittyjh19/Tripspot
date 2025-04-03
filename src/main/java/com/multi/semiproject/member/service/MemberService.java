package com.multi.semiproject.member.service;

import com.multi.semiproject.member.model.dao.MemberMapper;
import com.multi.semiproject.member.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MemberService {
    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public MemberService(MemberMapper memberMapper, BCryptPasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registMember(MemberDTO memberDTO) {
        String encodePwd = passwordEncoder.encode(memberDTO.getPw());
        memberDTO.setPw(encodePwd);

        if(memberDTO.getId().equalsIgnoreCase("admin"))
            memberDTO.setMemberRole("ROLE_ADMIN");

        int result = memberMapper.registMember(memberDTO);
        if (result <= 0) {
            throw new RuntimeException("registMember failed : count = 0");
        }
    }

    @Transactional(readOnly = true)
    public boolean checkIdExists(String id) {
        MemberDTO memberDTO = memberMapper.findMemberById(id);
        return memberDTO != null;  // If memberDTO is not null, the ID exists
    }
}

