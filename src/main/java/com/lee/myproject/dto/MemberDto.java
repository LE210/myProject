package com.lee.myproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lee.myproject.dto.board.BoardDto;
import com.lee.myproject.dto.board.CommentDto;
import com.lee.myproject.entity.Member;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // 값이 null 인 경우 json 제외
public class MemberDto {

    private String name;
    private int age;
    private String address;

    private List<OrderDto> orderList;
    private List<BoardDto> boardDtoList;
    private List<CommentDto> commentDtoList;

    public MemberDto(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public MemberDto() {

    }

    // 하나의 멤버와 오더리스트 함께 출력
    public static MemberDto memberWithOrder (Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setName(member.getName());
        memberDto.setAge(member.getAge());
        memberDto.setAddress(member.getAddress());

        List<OrderDto> orderDtos = member.getOrder().stream().map(OrderDto::form).collect(Collectors.toList());
        memberDto.setOrderList(orderDtos);
        // board list
        // comment list
        return memberDto;
    }

    // 하나의 멤버와 게시글을 함께 출력
    public static MemberDto memberWithBoard (Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setName(member.getName());
        memberDto.setAge(member.getAge());
        member.setAddress(member.getAddress());

        List<BoardDto> boardDtos = member.getBoardList()
                .stream().map(BoardDto::form).collect(Collectors.toList());
        memberDto.setBoardDtoList(boardDtos);

        return memberDto;

    }

//    public static MemberDto memberWithComment () {
//
//    }
}
