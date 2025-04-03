package com.multi.semiproject.board.service;

import com.multi.semiproject.board.model.dao.BoardMapper;
import com.multi.semiproject.board.model.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService  {


    private final BoardMapper boardMapper;

    public List<BoardDTO> getAllBoards() {
        List<BoardDTO> boardDTOS = boardMapper.selectAllBoards();
        return boardDTOS;
    }

    public BoardDTO getBoardById(int id) {
        boardMapper.increaseViewCount(id);
        return boardMapper.selectBoardById(id);
    }


    public void insertBoard(BoardDTO board) {
        boardMapper.insertBoard(board);
    }


    public void updateBoard(BoardDTO board) {
        boardMapper.updateBoard(board);
    }

    public void deleteBoard(int id) {
        boardMapper.deleteBoard(id);
    }
}
