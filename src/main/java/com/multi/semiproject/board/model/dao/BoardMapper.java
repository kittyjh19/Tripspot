package com.multi.semiproject.board.model.dao;

import com.multi.semiproject.board.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


public interface BoardMapper {
    int insertBoard(BoardDTO board);

    List<BoardDTO> selectBoardAll();
    Optional<BoardDTO> selectBoardByNo(@Param("no") int no);
    List<BoardDTO> selectBoardListById(@Param("id") String id);

    int updateBoard(BoardDTO board);
    int increaseViewCount(@Param("no") int no);

    int deleteBoard(@Param("no")int no, @Param("id") String id);
    int deleteBoardByAdmin(int no);




}
