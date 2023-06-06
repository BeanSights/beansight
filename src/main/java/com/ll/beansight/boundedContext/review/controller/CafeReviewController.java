package com.ll.beansight.boundedContext.review.controller;

import com.ll.beansight.base.rq.Rq;
import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.service.CafeInfoService;
import com.ll.beansight.boundedContext.review.service.CafeReviewService;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import com.ll.beansight.boundedContext.tag.service.TagService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cafeInfo")
public class CafeReviewController {
    private final CafeReviewService cafeReviewService;
    private final CafeInfoService cafeInfoService;
    private final TagService tagService;
    private final Rq rq;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/review") // 리뷰 작성 페이지
    public String cafeReview(@RequestParam("cafeId") Long cafeId, Model model) {
        Optional<CafeInfo> cafeInfo = cafeInfoService.findByCafeId(cafeId);
        List<Tag> tagList = tagService.getTagList();

        if (cafeInfo.isPresent()){
            CafeInfo info = cafeInfo.get();
            model.addAttribute("cafeInfo", info);
            model.addAttribute("tagList", tagList);
        }
        System.out.println(tagList);

        return "usr/cafeInfo/review";
    }

    @AllArgsConstructor // @Setter 도 가능, 데이터를 저장할 방편을 마련하기 위해서
    @Getter
    public static class ReviewForm {
        @NotBlank // 비어있지 않아야 하고, 공백으로만 이루어 지지도 않아야 한다.
        @Size(min = 4, max = 30) // 4자 이상, 30자 이하
        private final String content;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/review")
    public String cafeReview(
            @Valid @ModelAttribute ReviewForm reviewForm,
            @RequestParam("cafeId") Long cafeId,
            HttpServletRequest req
            ) { // 매개변수 카페정보 추가해서 write 메서드에 넘겨줘야함
        Map<String, String> cafeTags = new HashMap<>();


        RsData<CafeInfo> reviewRs = cafeReviewService.write(cafeId, reviewForm.getContent(), rq.getMember().getId());
        Optional<CafeInfo> cafeInfo = cafeInfoService.findByCafeId(cafeId);
        if (cafeInfo.isEmpty()){
            return rq.historyBack(reviewRs);
        }
        if (reviewRs.isFail()) {
            // 뒤로가기 하고 거기서 메세지 보여줘
            return rq.historyBack(reviewRs);
        }

        System.out.println(cafeTags);

        // 아래 링크로 리다이렉트(302, 이동) 하고 그 페이지에서 메세지 보여줘
        return rq.redirectWithMsg("/cafeInfo?x=" + cafeInfo.get().getX() + "&y=" + cafeInfo.get().getY(), reviewRs);
    }
}
