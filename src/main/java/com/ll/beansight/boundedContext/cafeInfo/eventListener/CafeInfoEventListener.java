package com.ll.beansight.boundedContext.cafeInfo.eventListener;

import com.ll.beansight.base.event.EventAfterCancelReview;
import com.ll.beansight.base.event.EventAfterModifyTagType;
import com.ll.beansight.base.event.EventAfterWriteReview;
import com.ll.beansight.boundedContext.cafeInfo.service.CafeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class CafeInfoEventListener {
    private final CafeInfoService cafeInfoService;

    @EventListener
    public void listen(EventAfterWriteReview event) {
        cafeInfoService.whenAfterWriteReview(event.getReviewTags());
    }

    @EventListener
    public void listen(EventAfterCancelReview event) {
        cafeInfoService.whenAfterCancelReview(event.getReview());
    }

    @EventListener
    public void listen(EventAfterModifyTagType event) {
        cafeInfoService.whenAfterModifyTagType(event.getReview(), event.getOldTagTypeCode());
    }
}
