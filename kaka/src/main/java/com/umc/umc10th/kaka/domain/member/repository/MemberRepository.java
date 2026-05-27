package com.umc.umc10th.kaka.domain.member.repository;

import com.umc.umc10th.kaka.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    void deleteByName(String name);
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
}
