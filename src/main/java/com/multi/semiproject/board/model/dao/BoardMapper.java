package com.multi.semiproject.board.model.dao;

import com.multi.semiproject.board.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


public interface BoardMapper {

    int insertBoard(BoardDTO board);

    List<BoardDTO> selectAllBoards();

    Optional<BoardDTO> selectBoardByNo(@Param("no") int no);


    List<BoardDTO> getAllBoardListById(@Param("id") String id);



    void increaseViewCount(@Param("no") int no);


    int updateBoard(BoardDTO board);


    int deleteBoard(@Param("no")int no, @Param("id") String id);
    int deleteBoardAdmin(int no);
}
