package com.multi.semiproject.board.model.dao;

import com.multi.semiproject.board.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


public interface BoardMapper {

    List<BoardDTO> selectAllBoards();
    List<BoardDTO> getAllBoardListById(@Param("id") String id);
    Optional<BoardDTO> selectBoardByNo(@Param("no") int no);
    void increaseViewCount(@Param("id") int id);

    void insertBoard(BoardDTO board);
    void updateBoard(BoardDTO board);
    void deleteBoard(int id);

}
