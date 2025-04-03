//package com.multi.semiproject.board.service;
//
//import com.multi.semiproject.board.model.dao.BoardMapper;
//import com.multi.semiproject.board.model.dto.BoardDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BoardServiceImpl implements BoardService {
//
//    @Autowired
//    private BoardMapper boardMapper;
//
//    @Override
//    public List<BoardDTO> getAllBoards() {
//        return boardMapper.selectAllBoards();
//    }
//
//    @Override
//    public BoardDTO getBoardById(int id) {
//        boardMapper.increaseViewCount(id);
//        return boardMapper.selectBoardById(id);
//    }
//
//    @Override
//    public void insertBoard(BoardDTO board) {
//        boardMapper.insertBoard(board);
//    }
//
//    @Override
//    public void updateBoard(BoardDTO board) {
//        boardMapper.updateBoard(board);
//    }
//
//    @Override
//    public void deleteBoard(int id) {
//        boardMapper.deleteBoard(id);
//    }
//}
