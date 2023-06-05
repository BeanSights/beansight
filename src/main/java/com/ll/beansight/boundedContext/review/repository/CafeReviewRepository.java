package com.ll.beansight.boundedContext.review.repository;

import com.ll.beansight.boundedContext.review.entity.CafeReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeReviewRepository extends JpaRepository<CafeReview, Long> {
}
