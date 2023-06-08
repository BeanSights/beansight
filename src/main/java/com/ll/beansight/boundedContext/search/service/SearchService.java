package com.ll.beansight.boundedContext.search.service;

import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.base.api.service.KakaoSearchService;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.repository.CafeInfoRepository;
import com.ll.beansight.boundedContext.search.entity.Cafe;
import com.ll.beansight.boundedContext.search.repository.CafeRepository;
import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import com.ll.beansight.boundedContext.tag.repository.CafeTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final KakaoSearchService kakaoSearchService;
    private final CafeRepository cafeRepository;
    private final CafeInfoRepository cafeInfoRepository;
    private final CafeTagRepository cafeTagRepository;
    // 10KM내에 검색
    private static final double RADIUS_KM = 10.0;

    // 키워드로 카페정보를 불러오는 서비스
    public List<DocumentDTO> keywordSearch(String keyword, double x, double y) {
        List<DocumentDTO> responses = kakaoSearchService.requestKeywordSearch(keyword, x, y).getDocumentDTOList();
        saveCafe(responses);
        return responses;
    }

    // 거리순으로만 카페정보를 불러오느 서비스
    public List<DocumentDTO> nearSearch(double x, double y) {
        List<DocumentDTO> responses = kakaoSearchService.requestCategorySearch(x, y, 0).getDocumentDTOList();
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

    // 태그 기준으로 필터링
    public List<CafeInfo> tagFilter(List<String> cafeType) {
        // 태그들을 Long으로 변환
        List<Long> tagList = cafeType.stream()
                .map(Long::parseLong).toList();
        System.out.println(tagList.size());
        Stream<CafeInfo> cafeInfoStream = cafeTagRepository.findAllByTag_TagId(tagList.get(0)).stream().map(CafeTag::getCafeInfo);

        // 필터링 작업.
        for(Long tag : tagList){
            cafeInfoStream = cafeInfoStream.filter(e -> cafeTagRepository.existsByTag_TagIdAndCafeInfo(tag, e));
        }
        System.out.println("태그 필터링 끝");
        return cafeInfoStream.toList();
    }

    // 거리 기준으로 필터링
    public List<CafeInfo> distanceFilter(List<CafeInfo> cafeInfoList, double x, double y) {
        System.out.println("거리 필터링 끝");
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
}
