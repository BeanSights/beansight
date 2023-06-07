package com.ll.beansight.boundedContext.member.controller;

import com.ll.beansight.base.rq.Rq;
import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.member.entity.Member;
import com.ll.beansight.boundedContext.member.entity.MemberWishList;
import com.ll.beansight.boundedContext.member.service.MemberService;
import com.ll.beansight.boundedContext.member.service.MemberWishListService;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import com.ll.beansight.boundedContext.tag.service.TagService;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member") // 액션 URL의 공통 접두어
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final TagService tagService;
    private final MemberWishListService memberWishListService;
    private final Rq rq;



    @PreAuthorize("isAnonymous()")
    @GetMapping("/login") // 로그인 폼, 로그인 폼 처리는 스프링 시큐리티가 구현, 폼 처리시에 CustomUserDetailsService 가 사용됨
    public String showLogin() {
        return "usr/member/login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/review") // 리뷰 작성 페이지
    public String review() {
        return "usr/member/review";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/wish") // 카페 성향 선택 페이지
    public String showWish(Model model) {
        List<Tag> tagList = tagService.getTagList();
        List<Tag> memberTagList = rq.getMember().getMemberTagList();
        List<Long> selectedTagIdList = new ArrayList<>();

        for (Tag tag : memberTagList) {
            selectedTagIdList.add(tag.getTagId());
        }

        model.addAttribute("tagList", tagList);
        model.addAttribute("selectedTagList", selectedTagIdList);
        System.out.println(selectedTagIdList);
        return "usr/member/wish";}

    @AllArgsConstructor
    @Getter
    public static class WishForm {
        private String selectedTags;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/wish") // 카페 성향 선택 후 설정
    public String wish(@Valid WishForm wishForm) {
        // Tag들의 ID를 배열로 저장
        List<String> selectedTags = List.of(wishForm.getSelectedTags().split(","));
        memberService.updateMemberTagList(rq.getMember(), selectedTags);
        return rq.redirectWithMsg("/", "hi");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public String me() {
        return "usr/member/me";
    }

    @AllArgsConstructor
    @Getter
    public static class WishListForm {
        @NotBlank
        @Size(min = 4, max = 30)
        private final String content;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/me")
    public String me(@RequestParam("id") Long memberId, @Valid @ModelAttribute WishListForm wishListForm) {

        RsData<MemberWishList> wishListRs = memberWishListService.createMemberWishList();
        return "usr/member/me";
    }

}