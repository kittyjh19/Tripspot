package com.multi.semiproject.member.controller;

import com.multi.semiproject.member.model.dto.MemberDTO;
import com.multi.semiproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public void memberLogin(){
    }



    @GetMapping("/register")
    public void memberRegist(){}

    @PostMapping("/register")
    public String registMember(MemberDTO memberDTO){

        memberService.registMember(memberDTO);
        return "redirect:/member/login";
    }
}

