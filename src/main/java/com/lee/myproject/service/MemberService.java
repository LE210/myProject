package com.lee.myproject.service;

import com.lee.myproject.dto.MemberDto;
import com.lee.myproject.dto.OrderDto;
import com.lee.myproject.entity.Member;
import com.lee.myproject.entity.MemberEt;
import com.lee.myproject.entity.Order;
import com.lee.myproject.repository.MemberRepo;
import com.lee.myproject.repository.MemberRepository;
import com.lee.myproject.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

    
    // 엔티티 매니저 방식
    // repository
    private final MemberRepository memberRepository;
    private final MemberRepo memberRepo;
    private final OrderRepo orderRepo;


    // 전체
    public List<MemberEt> findMembers() {
        return memberRepository.findAll();
    }

    // 찾기 1
    public MemberEt findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


    
    // Jpa 인터페이스 방식

    // 저장
    public int createMember(MemberDto dto) {
        List<Member> findMember = memberRepo.findByName(dto.getName());

        if (!findMember.isEmpty()) {
            return 1;
        }

        Member member = new Member();
        member.setName(dto.getName());
        member.setAge(dto.getAge());
        member.setAddress(dto.getAddress());
        memberRepo.save(member);

        return 0;
    }

    // 하나 찾기
    public MemberDto getMemberById(Long id) {
        Member member = memberRepo.findById(id).orElse(null);
        return convertToDTO(member);
    }

    // 전체 찾기
    @Transactional(readOnly = true)
    public List<MemberDto> getAllMembers() {
        List<Member> members = memberRepo.findAll();
        return members.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 수정
    public void updateMember(Long id, MemberDto dto) {
        Member member = memberRepo.findById(id).orElse(null);
        if (member != null) {
            member.setName(dto.getName());
            member.setAge(dto.getAge());
            member.setAddress(dto.getAddress());
            memberRepo.save(member);
        }
    }

    // 삭제
    public void deleteMember(Long id) {
        Member member = memberRepo.findById(id).orElse(null);
        if (member != null) {
            memberRepo.deleteById(id);
        } else {
            System.out.println("아이디 없음 ㄹㅇㅇㅇ");
        }
    }

    private MemberDto convertToDTO(Member member) {
        if (member == null) {
            return null;
        }

        MemberDto dto = new MemberDto();
        dto.setName(member.getName());
        dto.setAge(member.getAge());
        dto.setAddress(member.getAddress());
        return dto;
    }

    private OrderDto convertToDTO(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderDto.getId());
        orderDto.setStr(order.getStr());
        return orderDto;
    }

    // 오더 조회
    public List<OrderDto> getAllOrder() {
        List<Order> orders = orderRepo.findAll();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

//    // 함께 조회
//    @Transactional(readOnly = true)
//    public List<MemberDto> dtoAll () {
//        List<Member> members = memberRepo.findAll();
//        return members.stream()
//                .map(MemberDto::form)
//                .collect(Collectors.toList());
//    }

    // 한번에 쿼리를 조회, before > 멤버가 3명이면 쿼리를 3번 날렸음
    @Transactional(readOnly = true)
    public List<MemberDto> memberWithOrder() {
        List<Member> list = memberRepo.findAllWithOrders();
        return list.stream().map(MemberDto::memberWithOrder).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberDto> memberWithBoard() {
        List<Member> list = memberRepo.findMemberWithBoard();
        return list.stream().map(MemberDto::memberWithBoard).collect(Collectors.toList());
    }
}
