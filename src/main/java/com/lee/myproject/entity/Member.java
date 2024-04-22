package com.lee.myproject.entity;


import com.lee.myproject.entity.board.Board;
import com.lee.myproject.entity.board.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Members")
public class Member {

    /**
     * 멤버는 여러 게시물(주문)을 가질 수 있다.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private int age;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> order;

    @OneToMany(mappedBy = "member")
    private List<Board> boardList;

    @OneToMany
    private List<Comment> commentList;
}
