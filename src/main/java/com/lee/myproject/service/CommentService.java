package com.lee.myproject.service;

import com.lee.myproject.dto.board.CommentDto;
import com.lee.myproject.entity.Member;
import com.lee.myproject.entity.board.Board;
import com.lee.myproject.entity.board.Comment;
import com.lee.myproject.repository.BoardRepo;
import com.lee.myproject.repository.CommentRepo;
import com.lee.myproject.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final MemberRepo memberRepo;
    private final BoardRepo boardRepo;

    // 생성
     public void createComment(CommentDto commentDto, Long bdId, Long memId) {
        Member member = memberRepo.findById(memId).orElse(null);
        Board board = boardRepo.findById(bdId).orElse(null);
        if (member == null || board == null) {
            System.out.println("님, 아이디나 게시글이 없는데 댓글 어떻게 작성함?");
            return;
        }
        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setMember(member);
        comment.setContent(commentDto.getContent());
        comment.setAuthor(commentDto.getAuthor());
        comment.setDateTime(commentDto.getDateTime());

        commentRepo.save(comment);

    }


    public List<CommentDto> findBoardAll(Long id) {
         Board board = boardRepo.findById(id).orElse(null);
         if (board == null) {
             System.out.println("그런 게시글이 없음;");
         }
            List<Comment> list = commentRepo.findByBoardId(id);
        return list.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            commentDto.setAuthor(comment.getAuthor());
            commentDto.setContent(comment.getContent());
            commentDto.setDateTime(comment.getDateTime());
            return commentDto;
        }
        ).collect(Collectors.toList());
    }

    public void updateComment(CommentDto commentDto ,Long id) {
         Comment comment = commentRepo.findById(id).orElse(null);
        if (comment == null) {
            System.out.println("댓글 없음.");
        } else {
            comment.setContent(commentDto.getContent());
            comment.setAuthor(commentDto.getAuthor());
            comment.setDateTime(commentDto.getDateTime());
            commentRepo.save(comment);
        }
    }

    public void deleteComment(Long id) {
         Comment comment = commentRepo.findById(id).orElse(null);
         if (comment != null) {
             commentRepo.deleteById(id);
         }
    }
}
