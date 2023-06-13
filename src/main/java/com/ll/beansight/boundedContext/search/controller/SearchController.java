package com.ll.beansight.boundedContext.search.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.base.rq.Rq;
import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.member.service.MemberService;
import com.ll.beansight.boundedContext.search.response.CafeInfoResponse;
import com.ll.beansight.boundedContext.search.service.SearchService;
import com.ll.beansight.boundedContext.tag.entity.MemberTag;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import com.ll.beansight.boundedContext.tag.service.MemberTagService;
import com.ll.beansight.standard.util.Ut;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final Rq rq;
    private final SearchService searchService;
    private final MemberTagService memberTagService;

    // 거리순으로 카페 불러오는
    @GetMapping("/near-cafe")
    public ResponseEntity<RsData> nearCafe(Model model, @RequestParam(defaultValue = "126.97890911337976") double x,
                           @RequestParam(defaultValue = "37.571150829509854") double y){
        List<DocumentDTO> nearCafes = searchService.nearSearch(x, y);
        List<CafeInfoResponse> cafeInfoResponses = searchService.getCafeTags(nearCafes);
        return Ut.spring.responseEntityOf(RsData.of("S-1", "키워드로 장소 검색 성공", cafeInfoResponses));
    }

    // 키워드로 카페 불러오는
    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<RsData> keywordCafe(@PathVariable String keyword, Model model,
                                              @RequestParam(defaultValue = "126.97890911337976") double x, @RequestParam(defaultValue = "37.571150829509854") double y){
        if(keyword.length()==0){
            return Ut.spring.responseEntityOf(RsData.of("F-1", "키워드를 입력해주세요."));
        }
        List<DocumentDTO> keywordCafe = searchService.keywordSearch(keyword, x, y);
        // 키워드로 검색했는데 결과가 없는 경우.
        if(keywordCafe.size() == 0){
            return Ut.spring.responseEntityOf(RsData.of("F-1", "검색된 결과가 없습니다."));

        }
        List<CafeInfoResponse> cafeInfoResponses = searchService.getCafeTags(keywordCafe);
        return Ut.spring.responseEntityOf(RsData.of("S-1", "키워드로 장소 검색 성공", cafeInfoResponses));
    }

    // 필터링 기능
    @PostMapping("/filter")
    public ResponseEntity<RsData> filter(@RequestBody CafeFilterRequest request){
        System.out.println("Received x: " + request.x);
        System.out.println("Received y: " + request.y);
        System.out.println("Received cafeType: " + request.cafeType);
        // 태그 기준으로 1차 필터링
        List<CafeInfo> cafeInfoTagFilterList = searchService.tagFilter(request.cafeType);
        if(cafeInfoTagFilterList.size() == 0){
            return Ut.spring.responseEntityOf(RsData.of("F-1", "필터링된 결과가 없습니다."));
        }
        System.out.println(cafeInfoTagFilterList.size());
        // 카페 거리순 2차 필터링
        List<CafeInfo> cafeInfoDistanceFilterList = searchService.distanceFilter(cafeInfoTagFilterList, request.x, request.y);
        if(cafeInfoDistanceFilterList.size() == 0){
            return Ut.spring.responseEntityOf(RsData.of("F-1", "주변에 필터링된 카페가 없습니다."));
        }
        List<DocumentDTO> filterCafe = searchService.filterResponse(cafeInfoDistanceFilterList);

        List<CafeInfoResponse> cafeInfoResponses = searchService.getCafeTags(filterCafe);
        return Ut.spring.responseEntityOf(RsData.of("S-1", "필터링 장소 검색 성공", cafeInfoResponses));
    }

    // 추천 필터링 기능
    @GetMapping("/recommend")
    public ResponseEntity<RsData> recommendSearch(@RequestParam(defaultValue = "126.97890911337976") double x, @RequestParam(defaultValue = "37.571150829509854") double y){
        System.out.println("Received x: " + x);
        System.out.println("Received y: " + y);

        // 태그 기준으로 1차 필터링
        List<MemberTag> memberTags = memberTagService.findByMemberId(rq.getMember().getId());
        List<CafeInfo> cafeInfoTagFilterList = searchService.recommendTagFilter(memberTags);
        if(cafeInfoTagFilterList.size() == 0){
            return Ut.spring.responseEntityOf(RsData.of("F-1", "필터링된 결과가 없습니다."));
        }
        // 카페 거리순 2차 필터링
        List<CafeInfo> cafeInfoDistanceFilterList = searchService.distanceFilter(cafeInfoTagFilterList, x, y);
        if(cafeInfoDistanceFilterList.size() == 0){
            return Ut.spring.responseEntityOf(RsData.of("F-1", "주변에 필터링된 카페가 없습니다."));
        }
        List<DocumentDTO> filterCafe = searchService.filterResponse(cafeInfoDistanceFilterList);
        List<CafeInfoResponse> cafeInfoResponses = searchService.getCafeTags(filterCafe);
        return Ut.spring.responseEntityOf(RsData.of("S-1", "추천 장소 검색 성공", cafeInfoResponses));
    }

    public static class CafeFilterRequest {
        public double x;
        public double y;
        public List<String> cafeType;
    }
}
