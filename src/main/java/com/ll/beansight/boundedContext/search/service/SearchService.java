package com.ll.beansight.boundedContext.search.service;

import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.base.api.service.KakaoSearchService;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.repository.CafeInfoRepository;
import com.ll.beansight.boundedContext.cafeInfo.service.CafeInfoService;
import com.ll.beansight.boundedContext.search.controller.SearchController;
import com.ll.beansight.boundedContext.search.response.CafeInfoResponse;
import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import com.ll.beansight.boundedContext.tag.entity.MemberTag;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import com.ll.beansight.boundedContext.tag.repository.CafeTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final CafeInfoRepository cafeInfoRepository;
    private final KakaoSearchService kakaoSearchService;
    private final CafeInfoService cafeInfoService;
    private final CafeTagRepository cafeTagRepository;
    // 10KM내에 검색
    private static final double RADIUS_KM = 10.0;

    // 키워드로 카페정보를 불러오는 서비스
    public List<DocumentDTO> keywordSearch(String keyword, double x, double y) {
        List<DocumentDTO> responses = kakaoSearchService.requestKeywordSearch(keyword, x, y).getDocumentDTOList();
        return responses;
    }

    // 거리순으로만 카페정보를 불러오느 서비스
    public List<DocumentDTO> nearSearch(double x, double y) {
        List<DocumentDTO> responses = kakaoSearchService.requestCategorySearch(x, y, 10000).getDocumentDTOList();
        return responses;
    }

    // 태그 기준으로 필터링
    public List<CafeInfo> tagFilter(List<String> cafeType) {
        // 태그들을 Long으로 변환
        List<Long> tagList = cafeType.stream()
                .map(Long::parseLong).toList();
        System.out.println(tagList.size());
        Stream<CafeInfo> cafeInfoStream = cafeTagRepository.findAllByTag_TagId(tagList.get(0)).stream().map(CafeTag::getCafeInfo);

        // 필터링 작업.
        for(Long tag : tagList){
            cafeInfoStream = cafeInfoStream.filter(e -> cafeTagRepository.existsByTag_TagIdAndCafeInfo(tag, e)).distinct();
        }
        return cafeInfoStream.toList();
    }

    // 태그기준 필터링(추천검색)
    public List<CafeInfo> recommendTagFilter(List<MemberTag> tags) {

        List<Long> tagList = tags.stream()
                .map(MemberTag::getTag).map(Tag::getTagId).toList();
        Stream<CafeInfo> cafeInfoStream = cafeTagRepository.findAllByTag_TagId(tagList.get(0)).stream().map(CafeTag::getCafeInfo);

        // 필터링 작업.
        for(Long tag : tagList){
            cafeInfoStream = cafeInfoStream.filter(e -> cafeTagRepository.existsByTag_TagIdAndCafeInfo(tag, e)).distinct();
        }
        return cafeInfoStream.toList();
    }

    // 거리 기준으로 필터링
    public List<CafeInfo> distanceFilter(List<CafeInfo> cafeInfoList, double x, double y) {
        return cafeInfoList.stream().filter(cafe -> calculateDistance(x, y, cafe.getX(), cafe.getY()) <= RADIUS_KM).sorted((v1, v2) -> (int) (calculateDistance(x, y, v1.getX(), v1.getY()) - calculateDistance(x, y, v2.getX(), v2.getY()))).limit(15).toList();
    }

    // 유저와 카페의 거리 계산 알고리즘
    private double calculateDistance(double userLon, double userLat, double cafeLon, double cafeLat){
        userLat = Math.toRadians(userLat);
        userLon = Math.toRadians(userLon);
        cafeLat = Math.toRadians(cafeLat);
        cafeLon = Math.toRadians(cafeLon);

        double earthRadius = 6371;
        return earthRadius * Math.acos(Math.sin(userLat) * Math.sin(cafeLat) + Math.cos(userLat) * Math.cos(cafeLat) * Math.cos(userLon - cafeLon));
    }

    public List<DocumentDTO> filterResponse(List<CafeInfo> cafeInfoDistanceFilterList) {
        List<DocumentDTO> list = new ArrayList<>();
        for(CafeInfo cafe : cafeInfoDistanceFilterList){
            DocumentDTO dto = DocumentDTO.builder()
                    .id(cafe.getCafeId())
                    .longitude(cafe.getX())
                    .latitude(cafe.getY())
                    .placeName(cafe.getCafeName())
                    .addressName(cafe.getCafeAddress())
                    .phone(cafe.getCafePhoneNumber())
                    .build();
            list.add(dto);
        }
        return list;
    }

    // 카페 태그들을 가져오는
    public List<CafeInfoResponse> getCafeTags(List<DocumentDTO> nearCafes) {
        List<CafeInfoResponse> response = new ArrayList<>();
        for(DocumentDTO nearCafe : nearCafes){
            CafeInfo cafeInfo = cafeInfoRepository.findByCafeId(nearCafe.getId()).orElse(null);
            List<String> tagList = new ArrayList<>();
            // 카페 또는 태그가 있는 경우에만.
            if(cafeInfo != null){
                if(cafeInfo.getTagsCountByTypeCode() != null){
                    tagList = cafeInfoService.getCafeInfoTag(cafeInfo).keySet().stream()
                            .limit(3).toList();
                }
            }
            CafeInfoResponse cafeInfoResponse = CafeInfoResponse.builder()
                    .id(nearCafe.getId())
                    .placeName(nearCafe.getPlaceName())
                    .phone(nearCafe.getPhone())
                    .addressName(nearCafe.getAddressName())
                    .roadAddressName(nearCafe.getRoadAddressName())
                    .longitude(nearCafe.getLongitude())
                    .latitude(nearCafe.getLatitude())
                    .placeUrl(nearCafe.getPlaceUrl())
                    .distance(nearCafe.getDistance())
                    .cafeTag(tagList)
                    .build();
            response.add(cafeInfoResponse);
        }
        return response;
    }
}
