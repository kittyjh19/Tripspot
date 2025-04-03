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

    public List<BoardDTO> getAllBoards() {
        return boardMapper.selectAllBoards();
    }

    public List<BoardDTO> getAllBoardListById(String id) {
        return boardMapper.getAllBoardListById(id);
    }

    public Optional<BoardDTO> selectBoardByNo(int no) {
        return boardMapper.selectBoardByNo(no);
    }

    public void increaseViewCount(int id) {
        boardMapper.increaseViewCount(id);
    }




    //////////////////////////////////////////

//    public Optional<BoardDTO> selectBoardByNo(int no) {
//        //boardMapper.increaseViewCount(id);
//        return boardMapper.selectBoardByNo(no);
//    }


    public void insertBoard(BoardDTO board) {
        boardMapper.insertBoard(board);
    }


//    public int updateBoard(BoardDTO board) {
//        return boardMapper.updateBoard(board);
//    }

    public void deleteBoard(int id) {
        boardMapper.deleteBoard(id);
    }

}
