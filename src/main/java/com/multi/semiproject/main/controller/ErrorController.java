package com.multi.semiproject.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    //테스트용
    @PostMapping("/error")
    public String error(){
        return "error/error";
    }

    @RequestMapping("/denied")
    public void accessDenied(){}

    @PostMapping("/login")
    public void loginFailed(){}
}
