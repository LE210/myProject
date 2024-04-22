package com.lee.myproject.repository;

import com.lee.myproject.entity.MemberEt;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // entityManager
    final private EntityManager entityManager;

    // 저장

    // 전체
    public List<MemberEt> findAll() {
        return entityManager.createQuery("select m from MemberEt m", MemberEt.class)
                .getResultList();
    }

    // 찾기1
    public MemberEt findOne (Long id) {
        return entityManager.find(MemberEt.class, id);
    }

    // 전체


}
