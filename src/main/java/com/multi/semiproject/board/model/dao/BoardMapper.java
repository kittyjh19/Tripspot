package com.multi.semiproject.board.model.dao;

import com.multi.semiproject.board.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


public interface BoardMapper {
    int insertBoard(BoardDTO board);

    int selectBoardCount(); // 전체 게시글 수 조회
    int selectNormalBoardCount(); // 일반 게시글 수 조회
    List<BoardDTO> selectNoticeBoardList(); // 공지 게시글 목록 조회

    List<BoardDTO> selectBoardAll(@Param("offset") int offset, @Param("limit") int limit); // 페이징 처리
    List<BoardDTO> selectNormalBoardList(@Param("offset") int offset, @Param("limit") int limit); // 일반 게시글 페이징 처리

    Optional<BoardDTO> selectBoardByNo(@Param("no") int no);
    List<BoardDTO> selectBoardListById(@Param("id") String id);

    int updateBoard(BoardDTO board);
    int increaseViewCount(@Param("no") int no);

    int deleteBoard(@Param("no")int no, @Param("id") String id);
    int deleteBoardByAdmin(int no);

}
