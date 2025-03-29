package com.multi.semiproject.board.service;

import com.multi.semiproject.board.model.dao.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;


    //테스트용
    public int boardTest() {
        return boardMapper.boardTest();
    }
}
