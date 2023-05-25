package com.ll.beansight.base.api.service;

import com.ll.beansight.base.api.dto.KakaoApiResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class KakaoSearchService {

    private final RestTemplate restTemplate;
    private final KakaoUriBuilderService kakaoUriBuilderService;

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;

    // 카테고리 검색
    public KakaoApiResponseDTO requestCategorySearch(double longitude, double latitude) {
        if (isOutsideKoreanPeninsula(longitude, latitude)) {
            return null;
        }

        URI uri = kakaoUriBuilderService.buildUriCategorySearch(longitude, latitude);
        return callKakaoApi(uri);
    }

    // 키워드 검색
    public KakaoApiResponseDTO requestKeywordSearch(String keyword, double longitude, double latitude) {
        if (isOutsideKoreanPeninsula(longitude, latitude) || ObjectUtils.isEmpty(keyword)) {
            return null;
        }

        URI uri = kakaoUriBuilderService.buildUriKeywordSearch(keyword, longitude, latitude);
        return callKakaoApi(uri);
    }

    // 유효성 검사
    private boolean isOutsideKoreanPeninsula(double longitude, double latitude) {
        return latitude > 43 || latitude < 33 || longitude > 132 || longitude < 124;
    }

    private KakaoApiResponseDTO callKakaoApi(URI uri) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDTO.class).getBody();
    }
}
