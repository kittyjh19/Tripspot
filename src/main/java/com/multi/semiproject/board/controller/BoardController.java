package com.multi.semiproject.board.controller;

import com.multi.semiproject.authentication.dto.CustomUser;
import com.multi.semiproject.board.model.dto.BoardDTO;
import com.multi.semiproject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/write") //게시글 작성으로 이동
    public String writeForm(@AuthenticationPrincipal CustomUser user, Model model) {

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        return "board/insertform";
    }

    @PostMapping("/write") //게시글 작성
    public String writeSubmit(@AuthenticationPrincipal CustomUser user, BoardDTO board) {

        board.setMemberId(user.getId());
        int result = boardService.insertBoard(board);

        if(result==0){
            throw new RuntimeException("게시물을 작성할 수 없습니다.");
        }
        return "redirect:/board";
    }

    @GetMapping // 게시판 리스트 조회 //board list 가져오는 부분
    public String boardList(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int pageSize = 10; // 한 페이지에 보여줄 게시글 수
        // 공지글 목록 가져오기
        List<BoardDTO> noticeList = boardService.selectNoticeBoardList();

        // 일반 게시글 목록 가져오기 (페이지네이션 적용)
        int totalNormalCount = boardService.selectNormalBoardCount();
        int totalPages = (int) Math.ceil((double) totalNormalCount / pageSize);

        if (page < 1 || page > totalPages) {
            page = 1; // 잘못된 페이지 번호 처리
        }

        List<BoardDTO> normalList = boardService.selectNormalBoardList(page, pageSize - noticeList.size());

        // 공지글 + 일반글 합치기
        List<BoardDTO> boardList = new ArrayList<>();
        boardList.addAll(noticeList);
        boardList.addAll(normalList);

        if(boardList.isEmpty())
            throw new RuntimeException("게시물이 없습니다.");

        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "board/list";
    }


    @GetMapping("/detail/{no}") // 게시글 상세보기
    public String boardDetail(@PathVariable("no") int boardNo, @AuthenticationPrincipal CustomUser user, Model model) {

        BoardDTO board = boardService.selectBoardByNo(boardNo)
                .orElseThrow(() -> new IllegalArgumentException(boardNo + "번 게시글이 존재하지 않습니다."));

        int result = boardService.increaseViewCount(boardNo);
        if(result==0)
            throw new RuntimeException("게시글 조회수 업데이트 실패");

        board.setViewCount(board.getViewCount()+1);
        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        String currentUser = user.getId();

        model.addAttribute("currentUser",currentUser);
        model.addAttribute("isAdmin", isAdmin);

        model.addAttribute("board", board);
        return "board/detail";
    }


    @GetMapping("/list/user") //사용자가 작성한 모든 글 반환
    public String allBoardListByUser(@AuthenticationPrincipal CustomUser user,
                                     Model model) {

        List<BoardDTO> boardList = boardService.selectBoardListById(user.getId());
        if(boardList.isEmpty())
            throw new RuntimeException("사용자가 작성한 게시글이 없습니다.");
        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        String currentUser = user.getId();
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("boardList", boardList);
        return "board/listbyuser"; //아직 안됨
    }


    @GetMapping("/edit/{no}") //특정 게시글 수정 페이지 이동
    public String forwardToFormpage(@PathVariable int no,
                           @AuthenticationPrincipal CustomUser user,
                           Model model) {

        BoardDTO board = boardService.selectBoardByNo(no).orElseThrow(() -> new IllegalArgumentException(no+"번호의 게시글이 없습니다."));

        if (!board.getMemberId().equals(user.getUsername())) {
            throw new AccessDeniedException("수정 권한이 없습니다.");
        }

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("board", board);
        model.addAttribute("isAdmin", isAdmin);
        //model.addAttribute("actionUrl", "/board/edit/" + board.getId());
        return "board/form";
    }

    @PostMapping("/edit/{boardno}") //update 게시글
    public String updateBoard(@PathVariable("boardno") int no,
                              @AuthenticationPrincipal CustomUser user,
                              @ModelAttribute BoardDTO board) {

        board.setBoardNo(no);
        board.setMemberId(user.getId());

        int result = boardService.updateBoard(board);
        if(result == 0)
            throw new RuntimeException("수정 오류 발생");

        return "redirect:/board";

    }


    @GetMapping("/delete/{no}") //삭제
    public String deleteBoard(@PathVariable int no,
                              @AuthenticationPrincipal CustomUser user) {

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        int result = 0;
        if(isAdmin){ //관리자인 경우
            result = boardService.deleteBoardByAdmin(no);
        }else{ //일반 사용자인 경우
            String id = user.getId();
            result = boardService.deleteBoard(no,id);
        }
        if(result==0){
            throw new IllegalArgumentException("게시물을 삭제할 수 없습니다.");
        }

        return "redirect:/board";
    }

}