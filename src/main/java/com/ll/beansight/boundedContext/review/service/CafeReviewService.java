package com.ll.beansight.boundedContext.review.service;

import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.service.CafeInfoService;
import com.ll.beansight.boundedContext.review.repository.CafeReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeReviewService {
    private final CafeReviewRepository cafeReviewRepository;
    private final CafeInfoService cafeInfoService;

    public RsData<CafeInfo> write(Long cafeId, String content, Long memberId) {
//        cafeReviewRepository.save();
        Optional<CafeInfo> cafeInfo = cafeInfoService.findByCafeId(cafeId);
        return cafeInfo.map(cafe -> RsData.of("S-1", "리뷰작성이 완료되었습니다.", cafe)).orElseGet(() -> RsData.of("F-1", "리뷰작성이 실패했습니다."));
    }
}
