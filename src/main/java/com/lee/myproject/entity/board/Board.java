package com.lee.myproject.entity.board;

import com.lee.myproject.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
public class Board {

    /**
     * 아이디
     * 제목
     * 내용
     * 작성자
     * 작성 시간
     * 코멘트 : 게시물은 여러 댓글을 가질 수 있다.
     * 게시글은 하나의 멤버에 속한다.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String author;
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "board")
    private List<Comment> commentList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


}
