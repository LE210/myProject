package com.lee.myproject.repository;

import com.lee.myproject.entity.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    // 게시글의 id로 댓글을 찾는다.
    List<Comment> findByBoardId(Long id);
}
