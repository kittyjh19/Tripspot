package com.multi.semiproject.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class MainController {

    @GetMapping("/")
    public String main(){
        return "main/main";
    }

    //테스트용
    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }

    //테스트용
    @GetMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin";
    }

    //테스트용
    @GetMapping("/user")
    @ResponseBody
    public String user(){
        return "user";
    }

}
