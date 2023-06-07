package com.ll.beansight.boundedContext.cafeInfo.service;

import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.base.api.service.KakaoSearchService;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.repository.CafeInfoRepository;
import com.ll.beansight.boundedContext.review.entity.CafeReview;
import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import com.ll.beansight.boundedContext.tag.entity.ReviewTag;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import com.ll.beansight.boundedContext.tag.service.CafeTagService;
import com.ll.beansight.boundedContext.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeInfoService {
    private final CafeInfoRepository cafeInfoRepository;
    private final KakaoSearchService kakaoSearchService;
    private final TagService tagService;
    private final CafeTagService cafeTagService;

    public Optional<CafeInfo> findByCafeId(Long cafeId) {
        return cafeInfoRepository.findByCafeId(cafeId);
    }


    @Transactional
    public CafeInfo search(double x, double y) {
        //radius 0으로 하면 x,y 좌표가 일치하더라도 검색이 되지 않는 카페들이 존재해서 1로 바꿈 (거리순 정렬로 해서 처음거를 가져오면 된다)
        List<DocumentDTO> responses = kakaoSearchService.requestCategorySearch(x, y, 1).getDocumentDTOList();
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
        Optional<CafeInfo> cafeInfo = cafeInfoRepository.findByCafeId(reviewTags.get(0).getCafeReview().getCafeInfo().getCafeId());
        if (cafeInfo.isEmpty()) {
            return;
        }
        for (ReviewTag reviewTag : reviewTags) {
            cafeInfo.get().increaseTagCount(reviewTag.getTag().getTagId());
        }

        addCafeTags(cafeInfo.get());
    }

    public void whenAfterCancelReview(CafeReview review) {
    }

    public void whenAfterModifyTagType(CafeReview review, int oldTagTypeCode) {
    }

    public void addCafeTags(CafeInfo cafeInfo) {
        // 각각 tagsCountByTypeCode100부터 400까지를 Map 형태로 저장
        List<CafeTag> cafeTags = new ArrayList<>();
        Map<Long, Long> tagsCountByTypeCode = cafeInfo.getTagsCountByTypeCode();

        if (tagsCountByTypeCode == null) {
            return;
        }

        //value 기준으로 내림차순 정렬해서 Entry set 에 저장
        List<Long> listKeySet = new ArrayList<>(tagsCountByTypeCode.keySet());

        listKeySet.sort((o1, o2) -> (tagsCountByTypeCode.get(o2).compareTo(tagsCountByTypeCode.get(o1))));

        int i = 0; // 3개까지만 저장하기 위한 변수

        //cafeInfoResponse에서 Count된 것들 중 0보다 큰 것을 가져오고 정렬하여 3개까지만 저장
        for (Long key : listKeySet) {
            if (i == 3)  break;

            Optional<Tag> tag = tagService.getTag(key);
            if (tag.isEmpty()) {
                continue;
            }
            cafeTags.add(CafeTag.builder()
                    .cafeInfo(cafeInfo)
                    .tag(tag.get())
                    .build());
            i++;
        }


        cafeTagService.add(cafeTags);

    }
}

