package com.umc.umc10th.kaka.domain.member.repository;

import com.umc.umc10th.kaka.domain.member.entity.Term;
import com.umc.umc10th.kaka.domain.member.enums.TermName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TermRepository extends JpaRepository<Term, Long> {
    Optional<Term> findByName(TermName name);
}
