package com.lee.myproject.controller;

import com.lee.myproject.dto.board.BoardDto;
import com.lee.myproject.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 생성
    @PostMapping("/create/{id}") // 유저의 아이디 값을 가져옴 글쓴이가 누군지는 알아야지
    public Long createBoard (@RequestBody BoardDto boardDto, Long id) {
        Long suc = boardService.save(boardDto, id);
        // 성공 하면 1 아니면 0을 반환 하여 화면에 출력함.
        return (suc == 1L) ? 1L : 0;
    }

    // 게시글 조회
    @GetMapping("/find/{id}")
    public BoardDto getBoardById (@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    // 게시글 키워드로 조회 (아이디, 이름, 제목)

    // 게시글 전체 조회
    @GetMapping("/findAll")
    public List<BoardDto> getAllBoards ()  {
        return boardService.getAllBoards();
    }

    // 게시글 수정
    @PostMapping("/update/{id}")
    public void updateBoard (@RequestBody BoardDto boardDto, @PathVariable Long id) {
        boardService.updateBoard(boardDto, id);
    }

    // 게시글 삭제
    @PostMapping("/delete/{id}")
    public void deleteBoard (@PathVariable Long id) {
        boardService.deleteBoard(id);
    }


}
