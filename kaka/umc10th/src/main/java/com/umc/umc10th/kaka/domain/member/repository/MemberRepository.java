package com.umc.umc10th.kaka.domain.member.repository;

import com.umc.umc10th.kaka.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    void deleteByName(String name);
}
