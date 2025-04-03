//package com.multi.semiproject.board.controller;
//
//import com.multi.semiproject.board.model.dto.BoardDTO;
//import com.multi.semiproject.board.service.BoardService;
//import com.multi.semiproject.authentication.dto.CustomUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/board")
//public class BoardController {
//
//    @Autowired
//    private BoardService boardService;
//
//    @GetMapping
//    public String boardList(Model model) {
//        List<BoardDTO> list = boardService.getAllBoards();
//        model.addAttribute("boardList", list);
//        return "board/list";
//    }
//
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
//    @GetMapping("/{id}")
//    public String boardDetail(@PathVariable int id,
//                              @AuthenticationPrincipal CustomUser user,
//                              Model model) {
//        BoardDTO board = boardService.getBoardById(id);
//        boolean isAdmin = user.getAuthorities().stream()
//                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
//
//        model.addAttribute("board", board);
//        model.addAttribute("currentUser", user.getUsername());
//        model.addAttribute("isAdmin", isAdmin);
//        return "board/detail";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editForm(@PathVariable int id,
//                           @AuthenticationPrincipal CustomUser user,
//                           Model model) {
//        BoardDTO board = boardService.getBoardById(id);
//        if (!board.getWriter().equals(user.getUsername())) {
//            throw new AccessDeniedException("수정 권한이 없습니다.");
//        }
//        model.addAttribute("board", board);
//        model.addAttribute("actionUrl", "/board/edit/" + board.getId());
//        return "board/form";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String editSubmit(@PathVariable int id,
//                             @AuthenticationPrincipal CustomUser user,
//                             @ModelAttribute BoardDTO board) {
//        BoardDTO existing = boardService.getBoardById(id);
//        if (!existing.getWriter().equals(user.getUsername())) {
//            throw new AccessDeniedException("수정 권한이 없습니다.");
//        }
//        board.setId(id);
//        board.setWriter(user.getUsername());
//        boardService.updateBoard(board);
//        return "redirect:/board/" + id;
//    }
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
//}