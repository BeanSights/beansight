package com.ll.beansight.base.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class KakaoUriBuilderService {

    private static final String KAKAO_SEARCH_PLACES_BY_CATEGORY_URL = "https://dapi.kakao.com/v2/local/search/category.json";
    private static final String KAKAO_SEARCH_PLACES_BY_KEYWORD_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

    public URI buildUriCategorySearch(double longitude, double latitude) {
        return buildUri(KAKAO_SEARCH_PLACES_BY_CATEGORY_URL, longitude, latitude, null);
    }

    public URI buildUriKeywordSearch(String keyword, double longitude, double latitude) {
        return buildUri(KAKAO_SEARCH_PLACES_BY_KEYWORD_URL, longitude, latitude, keyword);
    }

    private URI buildUri(String baseUrl, double longitude, double latitude, String keyword) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        uriBuilder.queryParam("x", longitude);
        uriBuilder.queryParam("y", latitude);
        uriBuilder.queryParam("radius", 10000);
        uriBuilder.queryParam("size", 15);
        uriBuilder.queryParam("sort", "distance");

        if (keyword != null) {
            uriBuilder.queryParam("query", keyword);
        }

        uriBuilder.queryParam("category_group_code", "CE7");

        return uriBuilder.build().encode().toUri();
    }
}
