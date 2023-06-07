package com.ll.beansight.boundedContext.cafeInfo.service;

import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.base.api.service.KakaoSearchService;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.repository.CafeInfoRepository;
import com.ll.beansight.boundedContext.review.entity.CafeReview;
import com.ll.beansight.boundedContext.search.entity.Cafe;
import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import com.ll.beansight.boundedContext.tag.entity.ReviewTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeInfoService {
    private final CafeInfoRepository cafeInfoRepository;
    private final KakaoSearchService kakaoSearchService;

    public Optional<CafeInfo> findByCafeId(Long cafeId) {
        return cafeInfoRepository.findByCafeId(cafeId);
    }


    @Transactional
    public CafeInfo search(double x, double y) {
        //radius 0으로 하면 x,y 좌표가 일치하더라도 검색이 되지 않는 카페들이 존재해서 1로 바꿈 (거리순 정렬로 해서 처음거를 가져오면 된다)
        List<DocumentDTO> responses = kakaoSearchService.requestCategorySearch(x,y,1).getDocumentDTOList();
        DocumentDTO response = responses.get(0);
        Optional<CafeInfo> cafeInfo = cafeInfoRepository.findByCafeId(response.getId());

        return cafeInfo.orElseGet(() -> saveCafe(response));

    }

    @Transactional
    public CafeInfo saveCafe(DocumentDTO response) {
        CafeInfo cafeInfo = CafeInfo.builder()
                .cafeAddress(response.getAddressName())
                .cafePhoneNumber(response.getPhone())
                .x(response.getLongitude())
                .y(response.getLatitude())
                .cafeName(response.getPlaceName())
                .cafeId(response.getId())
                .build();
        return cafeInfoRepository.save(cafeInfo);
    }

    @Transactional
    public void whenAfterWriteReview(List<ReviewTag> reviewTags) {
        for(ReviewTag reviewTag : reviewTags){
            Optional<CafeInfo> cafeInfo = cafeInfoRepository.findByCafeId(reviewTag.getCafeReview().getCafeInfo().getCafeId());
            if(cafeInfo.isPresent()){
                cafeInfo.get().increaseTagCount(reviewTag.getTag().getTagId());
            }
        }

    }

    public void whenAfterCancelReview(CafeReview review) {
    }

    public void whenAfterModifyTagType(CafeReview review, int oldTagTypeCode) {
    }

    public void addCafeTags(List<CafeTag> cafeTags, CafeInfo cafeInfoResponse) {

        //cafeInfoResponse에서 Count된 것들 중 0보다 큰 것을 가져오고 정렬하여 3개까지만 저장


        CafeTag cafeTag = CafeTag.builder()
                .cafeInfo(cafeInfoResponse)
                .tag(null)
                .build();
    }
}

