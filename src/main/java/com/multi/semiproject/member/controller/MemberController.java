package com.multi.semiproject.member.controller;

import com.multi.semiproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;


    //테스트용
    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

    //테스트용
    @GetMapping("/logout")
    public String logout(){
        return "main/main";
    }

    //테스트용
    @GetMapping("/test")
    @ResponseBody
    public String memberTest(){
        int result = memberService.memberTest();
        return "member 테스트 결과 = "+result;

    }

}
