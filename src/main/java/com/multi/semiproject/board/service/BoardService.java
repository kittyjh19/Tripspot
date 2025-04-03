package com.multi.semiproject.board.service;

import com.multi.semiproject.board.model.dao.BoardMapper;
import com.multi.semiproject.board.model.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<BoardDTO> getAllBoardListById(String id){
        return boardMapper.getAllBoardListById(id);
    }

    //////////////////////////////////////////

    public Optional<BoardDTO> selectBoardByNo(int no) {

        return boardMapper.selectBoardByNo(no);
    }


    public int insertBoard(BoardDTO board) {
        return boardMapper.insertBoard(board);
    }


    public int updateBoard(BoardDTO board) {
        return boardMapper.updateBoard(board);
    }

    public int deleteBoard(int no, String id) {
        return boardMapper.deleteBoard(no, id);
    }

    public int deleteBoardAdmin(int no) {
        return boardMapper.deleteBoardAdmin(no);
    }

    public void increaseViewCount(int no) {
        boardMapper.increaseViewCount(no);
    }
}
