package com.lee.myproject.entity.board;

import com.lee.myproject.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Comment {

    /**
     * 아이디
     * 내용
     * 작성자
     * 작성 시간
     * 댓글은 하나의 게시글에 속한다.
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String author;
    private LocalDateTime dateTime;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
