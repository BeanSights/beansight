package com.ll.beansight.base.event;

import com.ll.beansight.boundedContext.review.entity.CafeReview;
import lombok.Getter;

@Getter
public class EventAfterModifyTagType {
    private final int oldTagTypeCode;
    private final CafeReview review;

    public EventAfterModifyTagType(CafeReview review, int oldTagTypeCode) {
        this.review = review;
        this.oldTagTypeCode = oldTagTypeCode;
    }
}
