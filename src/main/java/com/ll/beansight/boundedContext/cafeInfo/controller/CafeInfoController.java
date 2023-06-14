package com.ll.beansight.boundedContext.cafeInfo.controller;

import com.ll.beansight.base.rq.Rq;
import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfoWishList;
import com.ll.beansight.boundedContext.cafeInfo.service.CafeInfoService;
import com.ll.beansight.boundedContext.member.controller.MemberController;
import com.ll.beansight.boundedContext.member.entity.Member;
import com.ll.beansight.boundedContext.member.entity.MemberWishList;
import com.ll.beansight.boundedContext.member.service.MemberService;
import com.ll.beansight.boundedContext.member.service.MemberWishListService;
import com.ll.beansight.boundedContext.review.entity.CafeReview;
import com.ll.beansight.boundedContext.review.service.CafeReviewService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cafeInfo")
public class CafeInfoController {
    private final CafeInfoService cafeInfoService;
    private final Rq rq;
    private final MemberService memberService;
    private final CafeReviewService cafeReviewService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String showInfo(Model model, @RequestParam(defaultValue = "126.97890911337976") double x,
                           @RequestParam(defaultValue = "37.571150829509854") double y) {
        CafeInfo cafeInfoResponse = cafeInfoService.search(x, y);
        Map<String, Long> cafeInfoTag = cafeInfoService.getCafeInfoTag(cafeInfoResponse);
        List<CafeInfoWishList> cafeInfoWishList = cafeInfoService.getCafeInfo(rq.getMember(), cafeInfoResponse.getId());
        Boolean hasMemberCafeInfo = cafeInfoWishList.isEmpty(); // Boolean 객체로 변경
        model.addAttribute("hasMemberCafeInfo", hasMemberCafeInfo);
        model.addAttribute("member", rq.getMember());
        model.addAttribute("cafeInfo", cafeInfoResponse);
        model.addAttribute("cafeInfoTag", cafeInfoTag);

        return "usr/cafeInfo/showInfo";
    }

    @AllArgsConstructor
    @Getter
    public static class WishForm {
        @NotNull
        private final List<String> wishList;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public String showInfo(@Valid WishForm wishForm, @RequestParam(defaultValue = "126.97890911337976") double x,
                           @RequestParam(defaultValue = "37.571150829509854") double y) {
        CafeInfo cafeInfo = cafeInfoService.search(x, y);

        //cafeInfo를 wishList에 추가
        for (String wish : wishForm.getWishList()) {
            RsData<CafeInfoWishList> cafeInfoWishListRsData =cafeInfoService.addWishList(rq.getMember(), cafeInfo, wish);
            if (cafeInfoWishListRsData.isFail()) {
                return rq.historyBack(cafeInfoWishListRsData);
            }
        }


        return rq.redirectWithMsg("/cafeInfo?x=" + x + "&y=" + y, "위시리스트에 추가되었습니다.");
    }

    @AllArgsConstructor
    @Getter
    public static class ReviewForm {
        @NotBlank
        @Size(min = 4, max = 30)
        private final String content;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/review/update")
    public String updateReview(
            @RequestParam("id") Long memberId,
            @RequestParam("x") Double x,
            @RequestParam("y") Double y,
            @RequestParam("reviewId") Long reviewId,
            @Valid @ModelAttribute ReviewForm reviewForm
    ) {
        Optional<Member> member = memberService.findByMemberId(memberId);
        if (member.isEmpty()){
            return rq.redirectWithMsg("usr/member/login", "로그인 후 사용 가능합니다.");
        }
        RsData<CafeReview> reviewUpdateRs = cafeReviewService.updateReview(member.get(), reviewId, reviewForm.getContent());
        if (reviewUpdateRs.isFail()){
            rq.historyBack(RsData.of("F-1", "리뷰 수정에 실패했습니다."));
        }

        return rq.redirectWithMsg("/cafeInfo?x=" + x + "&y=" + y, reviewUpdateRs);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/review/delete")
    public String deleteReview(
            @RequestParam("x") Double x,
            @RequestParam("y") Double y,
            @RequestParam("reviewId") Long reviewId
    ) {
        Optional<Member> member = memberService.findByMemberId(rq.getMember().getId());
        if (member.isEmpty()){
            return rq.redirectWithMsg("usr/member/login", "로그인 후 사용 가능합니다.");
        }
        RsData<CafeReview> reviewDeleteRs = cafeReviewService.deleteReview(member.get(), reviewId);
        if (reviewDeleteRs.isFail()){
            rq.historyBack(RsData.of("F-1", "리뷰 삭제에 실패했습니다."));
        }

        return rq.redirectWithMsg("/cafeInfo?x=" + x + "&y=" + y, reviewDeleteRs);
    }
}
