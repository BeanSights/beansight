package com.ll.beansight.base.event;

import com.ll.beansight.boundedContext.review.entity.CafeReview;
import com.ll.beansight.boundedContext.search.entity.Cafe;
import lombok.Getter;

@Getter
public class EventAfterCancelReview {
    private final CafeReview review;

    public EventAfterCancelReview(CafeReview review) {
        this.review = review;
    }
}
