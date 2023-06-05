package com.ll.beansight.boundedContext.tag.repository;

import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeTagRepository extends JpaRepository<CafeTag, Long> {
}
