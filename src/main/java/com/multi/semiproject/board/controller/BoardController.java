package com.multi.semiproject.board.controller;


import com.multi.semiproject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;


    //테스트용
    @GetMapping("/test")
    @ResponseBody
    public String boardTest(){
        int result = boardService.boardTest();
        return "board 테스트 결과 = "+result;

    }
}
