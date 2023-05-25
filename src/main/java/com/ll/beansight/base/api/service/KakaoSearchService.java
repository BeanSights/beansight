package com.ll.beansight.base.api.service;

import com.ll.beansight.base.api.dto.KakaoApiResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoSearchService {

    private final RestTemplate restTemplate;

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;

    public KakaoApiResponseDTO requestKeywordSearch(double longitude, double latitude) {

    }

    public KakaoApiResponseDTO requestCategorySearch(double longitude, double latitude) {

    }
}
