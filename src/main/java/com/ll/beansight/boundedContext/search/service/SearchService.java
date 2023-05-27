package com.ll.beansight.boundedContext.search.service;

import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.base.api.dto.KakaoApiResponseDTO;
import com.ll.beansight.base.api.service.KakaoSearchService;
import com.ll.beansight.boundedContext.search.entity.Cafe;
import com.ll.beansight.boundedContext.search.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final KakaoSearchService kakaoSearchService;
    private final CafeRepository cafeRepository;

    // 키워드로 카페정보를 불러오는 서비스
    public List<DocumentDTO> keywordSearch(String keyword, double x, double y) {
        List<DocumentDTO> responses = kakaoSearchService.requestKeywordSearch(keyword, x, y).getDocumentDTOList();
        saveCafe(responses);
        return responses;
    }

    // 거리순으로만 카페정보를 불러오느 서비스
    public List<DocumentDTO> nearSearch(double x, double y) {
        List<DocumentDTO> responses = kakaoSearchService.requestCategorySearch(x, y).getDocumentDTOList();
        saveCafe(responses);
        return responses;
    }

    // 카페 정보를 저장하는 로직
    private void saveCafe(List<DocumentDTO> responses){
        for(DocumentDTO response : responses){
            if(cafeRepository.existsByKakaoCafeId(response.getId())) continue;
            Cafe cafe = Cafe.builder()
                    .cafeX(response.getLongitude())
                    .cafeY(response.getLatitude())
                    .cafeName(response.getPlaceName())
                    .kakaoCafeId(response.getId())
                    .build();
            cafeRepository.save(cafe);
        }
    }


}
