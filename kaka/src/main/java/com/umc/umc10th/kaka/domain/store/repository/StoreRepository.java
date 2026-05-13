package com.umc.umc10th.kaka.domain.store.repository;

import com.umc.umc10th.kaka.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}