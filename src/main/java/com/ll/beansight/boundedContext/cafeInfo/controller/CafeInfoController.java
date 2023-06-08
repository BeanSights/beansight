package com.ll.beansight.boundedContext.cafeInfo.controller;

import com.ll.beansight.base.rq.Rq;
import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.service.CafeInfoService;
import com.ll.beansight.boundedContext.member.entity.MemberWishList;
import com.ll.beansight.boundedContext.member.service.MemberWishListService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cafeInfo")
public class CafeInfoController {
    private final CafeInfoService cafeInfoService;
    private final Rq rq;
    private final MemberWishListService memberWishListService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String showInfo(Model model, @RequestParam(defaultValue = "126.97890911337976") double x,
                           @RequestParam(defaultValue = "37.571150829509854") double y) {
        CafeInfo cafeInfoResponse = cafeInfoService.search(x, y);
        List<MemberWishList> memberWishLists = memberWishListService.getMemberWishLists(rq.getMember().getId());
        model.addAttribute("wishList", memberWishLists);
        model.addAttribute("cafeInfo", cafeInfoResponse);

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
            RsData<MemberWishList> memberWishListRsData =memberWishListService.addWishList(rq.getMember(), cafeInfo, wish);
            if (memberWishListRsData.isFail()) {
                return rq.redirectWithMsg("/cafeInfo?x=" + x + "&y=" + y, memberWishListRsData.getMsg());
            }
        }


        return rq.redirectWithMsg("/cafeInfo?x=" + x + "&y=" + y, "good");
    }
}
