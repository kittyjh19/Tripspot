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

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping // 게시판 리스트 조회
    public String boardList(Model model) {
        List<BoardDTO> boardlists = boardService.getAllBoards();
        model.addAttribute("boardList", boardlists);
        return "board/list";
    }

    @GetMapping("/detail/{no}") // 게시글 상세보기
    public String boardDetail(@PathVariable int no, Model model) {
        BoardDTO board = boardService.selectBoardByNo(no)
                .orElseThrow(() -> new IllegalArgumentException(no + "번 게시글이 존재하지 않습니다."));
        boardService.increaseViewCount(no); // 조회수 증가
        model.addAttribute("board", board);
        return "board/detail";
    }

//    @GetMapping("/write")
//    public String writeForm(Model model) {
//        model.addAttribute("actionUrl", "/board/write");
//        return "board/form";
//    }
//
//    @PostMapping("/write")
//    public String writeSubmit(@AuthenticationPrincipal CustomUser user, BoardDTO board) {
//        board.setWriter(user.getUsername());
//        boardService.insertBoard(board);
//        return "redirect:/board";
//    }
//
    @GetMapping("/list/user") //작성자가 작성한 모든 글 반환
    public String allBoardListByUser(@AuthenticationPrincipal CustomUser user,
                              Model model) throws Exception {

        List<BoardDTO> boardList = boardService.getAllBoardListById(user.getId());
        if(boardList.isEmpty())
            throw new Exception("작성한 글이 없습니다.");

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("boardList", boardList);
        model.addAttribute("currentUser", user.getUsername());
        model.addAttribute("isAdmin", isAdmin);
        return "board/detail";
    }
//
    @GetMapping("/edit/{no}") //특정 게시글 수정
    public String editForm(@PathVariable int no,
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
//
    @PostMapping("/edit")
    public String editSubmit(@RequestParam("boardno") int no,
                             @AuthenticationPrincipal CustomUser user,
                             @ModelAttribute BoardDTO board) {
        System.out.println("BoardController.editSubmit");
//        BoardDTO existing = boardService.selectBoardByNo(no).orElseThrow(() -> new IllegalArgumentException(no+"번호의 게시글이 없습니다."));

//        int result = boardService.updateBoard(board);
//        if (!existing.getWriter().equals(user.getUsername())) {
//            throw new AccessDeniedException("수정 권한이 없습니다.");
//        }
//        board.setId(id);
//        board.setWriter(user.getUsername());
//        boardService.updateBoard(board);
//        return "redirect:/board/" + id;
        return null;
    }
//
//    @PostMapping("/delete/{id}")
//    public String deleteBoard(@PathVariable int id,
//                              @AuthenticationPrincipal CustomUser user) {
//        BoardDTO board = boardService.getBoardById(id);
//        boolean isAdmin = user.getAuthorities().stream()
//                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
//
//        if (board.getWriter().equals(user.getUsername()) || isAdmin) {
//            boardService.deleteBoard(id);
//        } else {
//            throw new AccessDeniedException("삭제 권한이 없습니다.");
//        }
//        return "redirect:/board";
//    }
}