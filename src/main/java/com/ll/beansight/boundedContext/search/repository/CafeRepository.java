package com.ll.beansight.boundedContext.search.repository;

import com.ll.beansight.boundedContext.search.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
    boolean existsByKakaoCafeId(Long id);
}
