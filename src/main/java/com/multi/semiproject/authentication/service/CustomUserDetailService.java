package com.multi.semiproject.authentication.service;

import com.multi.semiproject.authentication.dto.CustomUser;
import com.multi.semiproject.member.model.dao.MemberMapper;
import com.multi.semiproject.member.model.dto.MemberDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberMapper memberMapper;

    public CustomUserDetailService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username :" + username);

        MemberDTO memberDTO = memberMapper.findMemberById(username);

        if(memberDTO == null){
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다");
        }
        // Check the raw role stored in the database
        System.out.println("Raw role from DB: " + memberDTO.getMemberRole());

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(memberDTO.getMemberRole()));

        System.out.println("Authorities: " + authorities);
        //debug

        return new CustomUser(memberDTO, authorities);
    }
    public Map<String, List<String>> getPermitListMap() {

        Map<String, List<String>> permitListMap = new HashMap<>();
        List<String> adminPermitList = new ArrayList<>();
        List<String> memberPermitList = new ArrayList<>();

        adminPermitList.add("/admin/dashboard");

        permitListMap.put("adminPermitList", adminPermitList);
        permitListMap.put("memberPermitList", memberPermitList);

        return permitListMap;
    }
}