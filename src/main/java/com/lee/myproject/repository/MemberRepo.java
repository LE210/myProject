package com.lee.myproject.repository;

import com.lee.myproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {

    // 쿼리 문을 여러번 내 보내지 않고 한번에 출력 하는 방법을 구안
    @Query("select distinct m from Member m left join fetch m.order")
    List<Member> findAllWithOrders();

    @Query("select distinct m from Member m left join fetch m.boardList")
    List<Member> findMemberWithBoard();

    List<Member> findByName(String name);
}
