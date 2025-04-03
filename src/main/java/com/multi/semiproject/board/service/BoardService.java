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

    public List<BoardDTO> selectBoardAll(int page, int pageSize) {
        int totalCount = boardMapper.selectBoardCount(); //전체 게시글 수 조회
        int totalPages = (int) Math.ceil((double) totalCount / pageSize); //전체 페이지 수 계산

        //잘못된 페이지 번호 요청 처리
        if (page < 1 || page > totalPages) {
            page = 1; // 첫 페이지로 설정
        }

        int offset = (page - 1) * pageSize; //offset 계산
        List<BoardDTO> boardList = boardMapper.selectBoardAll(offset, pageSize); //페이징된 게시글 목록 조회

        return boardList;
    }

    public int selectBoardCount(){
        return boardMapper.selectBoardCount();
    }

    public List<BoardDTO> selectNoticeBoardList() {
        return boardMapper.selectNoticeBoardList();
    }

    public List<BoardDTO> selectNormalBoardList(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return boardMapper.selectNormalBoardList(offset, pageSize);
    }

    public int selectNormalBoardCount() {
        return boardMapper.selectNormalBoardCount();
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
