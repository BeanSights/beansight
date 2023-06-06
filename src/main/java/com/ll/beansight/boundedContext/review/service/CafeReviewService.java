package com.ll.beansight.boundedContext.review.service;

import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.service.CafeInfoService;
import com.ll.beansight.boundedContext.member.entity.Member;
import com.ll.beansight.boundedContext.member.service.MemberService;
import com.ll.beansight.boundedContext.review.entity.CafeReview;
import com.ll.beansight.boundedContext.review.repository.CafeReviewRepository;
import com.ll.beansight.boundedContext.tag.entity.ReviewTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeReviewService {
    private final CafeReviewRepository cafeReviewRepository;
    private final CafeInfoService cafeInfoService;
    private final MemberService memberService;

    public RsData<CafeReview> write(Long cafeId, Long memberId, String content) {
        Optional<CafeInfo> cafeInfo = cafeInfoService.findByCafeId(cafeId);
        Optional<Member> member = memberService.findByMemberId(memberId);
        if (cafeInfo.isEmpty()){
            return RsData.of("F-1", "카페정보가 없습니다.");
        }
        if (member.isEmpty()){
            return RsData.of("F-1", "유저정보가 없습니다.");
        }
        CafeReview cafeReview = CafeReview
                .builder()
                .member(member.get())
                .content(content)
                .cafeInfo(cafeInfo.get())
                .build();

        cafeReviewRepository.save(cafeReview);

        return RsData.of("S-1", "리뷰가 작성되었습니다.", cafeReview);
    }
}
