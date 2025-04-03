package com.multi.semiproject.board.controller;

import com.multi.semiproject.board.model.dto.BoardDTO;
import com.multi.semiproject.authentication.dto.CustomUser;
import com.multi.semiproject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping //board list 가져오는 부분
    public String boardList(Model model) {
        List<BoardDTO> boardlists = boardService.getAllBoards();
        if(boardlists.isEmpty())
            throw new RuntimeException("게시물이 없습니다.");
        model.addAttribute("boardList", boardlists);
        return "board/list";
    }

    @GetMapping("/write") //게시글 작성으로 이동
    public String writeForm(@AuthenticationPrincipal CustomUser user, Model model) {
        //model.addAttribute("actionUrl", "/board/write");
        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        return "board/insertform";
    }
//
    @PostMapping("/write") //게시글 작성
    public String writeSubmit(@AuthenticationPrincipal CustomUser user, BoardDTO board) {
        //System.out.println("BoardController.writeSubmit");
        board.setMemberId(user.getId());
        int result = boardService.insertBoard(board);

        if(result==0){
            throw new RuntimeException("게시물을 작성할 수 없습니다.");
        }
//        board.setWriter(user.getUsername());
//        boardService.insertBoard(board);
        return "redirect:/board";
    }
//
    @GetMapping("/list/user") //사용자가 작성한 모든 글 반환
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
    @GetMapping("/edit/{no}") //특정 게시글 수정 페이지 이동
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
    @PostMapping("/edit") //update 게시글
    public String updateBoard(@RequestParam("boardno") int no,
                             @AuthenticationPrincipal CustomUser user,
                             @ModelAttribute BoardDTO board) {
        //System.out.println("BoardController.editSubmit");
//        BoardDTO existing = boardService.selectBoardByNo(no).orElseThrow(() -> new IllegalArgumentException(no+"번호의 게시글이 없습니다."));

        board.setMemberId(user.getId());
        //board.setModifiedDate(LocalDateTime.now());
        int result = boardService.updateBoard(board);
        if(result == 0)
            throw new RuntimeException("수정 오류 발생");

        return "redirect:board/";

//        if (!existing.getWriter().equals(user.getUsername())) {
//            throw new AccessDeniedException("수정 권한이 없습니다.");
//        }
//        board.setId(id);
//        board.setWriter(user.getUsername());
//        boardService.updateBoard(board);
//        return "redirect:/board/" + id;
        //return null;
    }
//
    @GetMapping("/delete/{no}") //삭제
    public String deleteBoard(@PathVariable int no,
                              @AuthenticationPrincipal CustomUser user) {

        System.out.println("BoardController.deleteBoard");

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        int result = 0;
        if(isAdmin){ //관리자인 경우
            result = boardService.deleteBoardAdmin(no);
        }else{ //일반 사용자인 경우
            String id = user.getId();
            result = boardService.deleteBoard(no,id);
        }
        if(result==0){
            throw new IllegalArgumentException("게시물을 삭제할 수 없습니다.");
        }

        //사용자인경우
//        System.out.println("BoardController.deleteBoard");
//        BoardDTO board = boardService.getBoardById(id);
//        boolean isAdmin = user.getAuthorities().stream()
//                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
//
//        if (board.getWriter().equals(user.getUsername()) || isAdmin) {
//            boardService.deleteBoard(id);
//        } else {
//            throw new AccessDeniedException("삭제 권한이 없습니다.");
//        }
        return "redirect:/board";
    }

    @GetMapping("/detail/{no}") //단건 조회
    public String selectBoard(@PathVariable int no, Model model,  @AuthenticationPrincipal CustomUser user){


        BoardDTO board = boardService.selectBoardByNo(no).orElseThrow(() -> new IllegalArgumentException(no+"번호의 게시글이 없습니다."));

        board.setViewCount(board.getViewCount()+1);

        boardService.increaseViewCount(no);

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("board", board);
        model.addAttribute("isAdmin", isAdmin);

        return "board/detail";
    }
}