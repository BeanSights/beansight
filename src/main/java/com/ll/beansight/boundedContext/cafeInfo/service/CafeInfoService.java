package com.ll.beansight.boundedContext.cafeInfo.service;

import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.base.api.service.KakaoSearchService;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.repository.CafeInfoRepository;
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

    public Optional<CafeInfo> findBycafeId(Long cafeId) {

        return cafeInfoRepository.findByCafeId(cafeId);
    }


    public DocumentDTO search(double x, double y) {
        List<DocumentDTO> responses = kakaoSearchService.requestCategorySearch(x, y).getDocumentDTOList();
        DocumentDTO response = responses.get(0);
//        saveCafe(responses);
        return response;
    }
}
