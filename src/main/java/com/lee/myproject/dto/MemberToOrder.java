package com.lee.myproject.dto;

import com.lee.myproject.entity.board.Board;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberToOrder extends MemberDto{

    private List<Board> list;

}
