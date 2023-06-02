package com.ll.beansight.boundedContext.cafeInfo.service;

import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.base.api.service.KakaoSearchService;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.repository.CafeInfoRepository;
import com.ll.beansight.boundedContext.search.entity.Cafe;
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
}
