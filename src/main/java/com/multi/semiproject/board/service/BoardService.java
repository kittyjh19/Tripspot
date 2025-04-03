package com.multi.semiproject.board.service;

import com.multi.semiproject.board.model.dao.BoardMapper;
import com.multi.semiproject.board.model.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService  {


    private final BoardMapper boardMapper;

    public int insertBoard(BoardDTO board) {
        return boardMapper.insertBoard(board);
    }

    public List<BoardDTO> selectBoardAll() {
        return boardMapper.selectBoardAll();
    }

    public Optional<BoardDTO> selectBoardByNo(int no) {

        return boardMapper.selectBoardByNo(no);
    }


    public int updateBoard(BoardDTO board) {
        return boardMapper.updateBoard(board);
    }
    public int increaseViewCount(int no) {
        return boardMapper.increaseViewCount(no);
    }

    public int deleteBoard(int no, String id) {
        return boardMapper.deleteBoard(no, id);
    }

    public int deleteBoardByAdmin(int no) {
        return boardMapper.deleteBoardByAdmin(no);
    }


    public List<BoardDTO> selectBoardListById(String id) {
        return boardMapper.selectBoardListById(id);
    }

}
