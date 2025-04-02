package com.multi.semiproject.board.service;

import com.multi.semiproject.board.model.dao.BoardMapper;
import com.multi.semiproject.board.model.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface BoardService {
    List<BoardDTO> getAllBoards();
    BoardDTO getBoardById(int id);
    void insertBoard(BoardDTO board);
    void updateBoard(BoardDTO board);
    void deleteBoard(int id);
}

