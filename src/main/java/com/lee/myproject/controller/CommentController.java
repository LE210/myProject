package com.lee.myproject.controller;

import com.lee.myproject.dto.board.CommentDto;
import com.lee.myproject.entity.board.Comment;
import com.lee.myproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.crypto.interfaces.PBEKey;
import java.util.List;

@RequestMapping("/api/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/create/{bdId}/{memId}")
    public void createComment (@RequestBody CommentDto commentDto, @PathVariable Long bdId, @PathVariable Long memId) {
        commentService.createComment(commentDto, bdId, memId);
    }

    // 댓글 조회 (게시글 하나에 대한 모든 댓글을 가져 오는게 일반적)
    @GetMapping("/find/{id}")
    public List<CommentDto> findBoardAll (@PathVariable Long id) {
        return commentService.findBoardAll(id);
    }

    // 댓글 키워드로 조회 (아이디, 이름, 제목)

    // 댓글 전체 조회 (이미 구현 됨)

    // 댓글 수정 (테스트안함)
    @PostMapping("/update/{id}")
    public void updateComment (@RequestBody CommentDto commentDto, @PathVariable Long id) {
        commentService.updateComment(commentDto, id);
    }


    // 댓글 삭제
    @PostMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
