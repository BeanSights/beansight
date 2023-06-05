package com.ll.beansight.base.event;


import com.ll.beansight.boundedContext.review.entity.CafeReview;
import lombok.Getter;

@Getter
public class EventAfterWriteReview {
    private final CafeReview review;

    public EventAfterWriteReview(CafeReview review) {
        this.review = review;
    }
}
