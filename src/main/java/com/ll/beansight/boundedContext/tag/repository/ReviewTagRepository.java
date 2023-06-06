package com.ll.beansight.boundedContext.tag.repository;

import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import com.ll.beansight.boundedContext.tag.entity.ReviewTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewTagRepository extends JpaRepository<ReviewTag, Long> {
}
