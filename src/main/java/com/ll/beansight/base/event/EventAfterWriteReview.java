package com.ll.beansight.base.event;


import com.ll.beansight.boundedContext.review.entity.CafeReview;
import com.ll.beansight.boundedContext.tag.entity.ReviewTag;
import lombok.Getter;

import java.util.List;

@Getter
public class EventAfterWriteReview {

    private final List<ReviewTag> reviewTags;

    public EventAfterWriteReview(List<ReviewTag> reviewTags) {
        this.reviewTags = reviewTags;
    }
}
