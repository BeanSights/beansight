package com.ll.beansight.boundedContext.search.controller;

import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.base.rq.Rq;
import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.repository.CafeInfoRepository;
import com.ll.beansight.boundedContext.search.entity.Cafe;
import com.ll.beansight.boundedContext.search.repository.CafeRepository;
import com.ll.beansight.boundedContext.search.service.SearchService;
import com.ll.beansight.standard.util.Ut;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final Rq rq;
    private final SearchService searchService;

    // 거리순으로 카페 불러오는
    @GetMapping("near-cafe")
    public String nearCafe(Model model, @RequestParam(defaultValue = "126.97890911337976") double x,
                           @RequestParam(defaultValue = "37.571150829509854") double y){
        List<DocumentDTO> nearCafeResponse = searchService.nearSearch(x, y);
        model.addAttribute("nearCafe", nearCafeResponse);
        return "usr/home/map";
    }

    // 키워드로 카페 불러오는
    @GetMapping("keyword/{keyword}")
    public ResponseEntity<RsData> keywordCafe(@PathVariable String keyword, Model model,
                                              @RequestParam(defaultValue = "126.97890911337976") double x, @RequestParam(defaultValue = "37.571150829509854") double y){
        if(keyword.length()==0){
            return Ut.spring.responseEntityOf(RsData.of("F-1", "키워드를 입력해주세요."));
        }
        List<DocumentDTO> keywordResponse = searchService.keywordSearch(keyword, x, y);
        // 키워드로 검색했는데 결과가 없는 경우.
        if(keywordResponse.size() == 0){
            return Ut.spring.responseEntityOf(RsData.of("F-1", "검색된 결과가 없습니다."));

        }

        return Ut.spring.responseEntityOf(RsData.of("S-1", "키워드로 장소 검색 성공", keywordResponse));
    }

    // 필터링 기능
    @PostMapping("filter/{keyword}")
    public RsData<List<DocumentDTO>> filter(Model model,
                                                 @RequestParam(defaultValue = "126.97890911337976") double x, @RequestParam(defaultValue = "37.571150829509854") double y,
                                            @RequestBody FilterRequest filterRequest){

        List<CafeInfo> cafeInfos = searchService.filterByTags(filterRequest);
        if(cafeInfos.size() == 0){
            return RsData.of("F-1", "검색된 결과가 없습니다.");
        }



        return RsData.of("S-1", "필터링 성공");
    }

    @Data
    public static class FilterRequest {
        private List<Integer> tags;
    }
}
