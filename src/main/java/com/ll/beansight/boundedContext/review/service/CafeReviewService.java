package com.ll.beansight.boundedContext.review.service;

import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.cafeInfo.repository.CafeInfoRepository;
import com.ll.beansight.boundedContext.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeReviewService {
    private final CafeInfoRepository cafeInfoRepository;

    public RsData<Member> write(String content, Long id) {
//        return cafeInfoRepository.save();
        return null;
    }
}
