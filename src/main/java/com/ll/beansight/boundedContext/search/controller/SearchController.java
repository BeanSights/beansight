package com.ll.beansight.boundedContext.search.controller;

import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.base.rq.Rq;
import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public RsData<List<DocumentDTO>> keywordCafe(@PathVariable String keyword, Model model,
                              @RequestParam(defaultValue = "126.97890911337976") double x, @RequestParam(defaultValue = "37.571150829509854") double y){
        if(keyword.isEmpty()){
            return RsData.of("F-1", "키워드를 입력해주세요.");
        }
        List<DocumentDTO> keywordResponse = searchService.keywordSearch(keyword, x, y);
        // 키워드로 검색했는데 결과가 없는 경우.
        if(keywordResponse.size() == 0){
            return RsData.of("F-1", "검색된 결과가 없습니다.");
        }

        return RsData.of("S-1", "키워드로 장소 검색 성공", keywordResponse);
    }



}
