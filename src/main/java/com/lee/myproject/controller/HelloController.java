package com.lee.myproject.controller;

import com.lee.myproject.dto.MemberDto;
import com.lee.myproject.dto.ResponseTest;
import com.lee.myproject.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/member")
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequiredArgsConstructor
public class HelloController {

    // service
    private final MemberService memberService;

    @GetMapping("")
    public String hello() {
        return "hello world!";
    }

//    @GetMapping("/api/members")
//    public Result memberList() {
//        List<MemberEt> findMembers = memberService.findMembers();
//        List<MemberDto> dtos = findMembers.stream()
//                .map(m -> new MemberDto(m.getName(), m.getAge(), m.getAddress()))
//                .collect(Collectors.toList());
//        return new Result("반갑습니다.", dtos);
//    }

    @Data
    @AllArgsConstructor
    private class Result<T> {
        private T notice;
        private T data;
    }

    // Jpa 인터페이스 방식

    // 멤버 저장
    @PostMapping("/create")
    public ResponseTest createMember(@RequestBody MemberDto dto) {
        return new ResponseTest(memberService.createMember(dto));
    }
//    // 멤버 저장
//    @PostMapping("/create")
//    public void createMember(@RequestBody MemberDto dto) {
//        memberService.createMember(dto);
//    }

//    // 멤버 조회 id 로 조회
//    @GetMapping("/find/{id}")
//    public MemberDto getMemberById(@PathVariable Long id) {
//        return memberService.getMemberById(id);
//    }

    // 멤버 조회 id 로 조회
    @GetMapping("/find/{id}")
    public ResponseTest<MemberDto> getMemberById(@PathVariable Long id) {
        List<MemberDto> list = new ArrayList<>();
        list.add(memberService.getMemberById(id));

        return new ResponseTest<>(list);
    }


    // 멤버 키워드 검색 (?)

    // 멤버 전체
    @GetMapping("/findAll")
    public ResponseTest<MemberDto> getAllMembers() {

//        List<MemberDto> allMembers = memberService.getAllMembers();
        return new ResponseTest<>(memberService.getAllMembers());
    }
//    @GetMapping("/findAll")
//    public List<MemberDto> getAllMembers() {
//        return memberService.getAllMembers();
//    }


    // 멤버 수정
    @PostMapping("/update/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody MemberDto dto) {
        memberService.updateMember(id, dto);
    }

    // 멤버 삭제
    @PostMapping("/delete/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }

    // 멤버와 주문 함께 조회
    @GetMapping("/memberWithOrder")
    public List<MemberDto> memberWithOrder () {
        return memberService.memberWithOrder();
    }

    // 멤버와 게시글 함께 조회
    @GetMapping("/memberWithBoard")
    public List<MemberDto> memberWithBoard () {
        return memberService.memberWithBoard();
    }

}
