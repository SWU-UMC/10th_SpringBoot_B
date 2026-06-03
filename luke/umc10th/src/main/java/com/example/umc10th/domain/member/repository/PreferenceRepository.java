package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.mapping.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository
        extends JpaRepository<Preference, Long> {
}