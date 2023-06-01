package com.ll.beansight.boundedContext.member.controller;

import com.ll.beansight.base.rq.Rq;
import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.member.entity.Member;
import com.ll.beansight.boundedContext.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member") // 액션 URL의 공통 접두어
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
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

    @PreAuthorize("isAnonymous()")
    @GetMapping("/wish") // 카페 성향 선택 페이지
    public String showWish() { return "usr/member/wish";}

    @PreAuthorize("isAnonymous()")
    @PostMapping("/wish") // 카페 성향 선택 후 설정
    public String wish() { return rq.redirectWithMsg("/", "null");}

    @PreAuthorize("isAnonymous()")
    @GetMapping("/me") // 리뷰 작성 페이지
    public String me() {
        return "usr/member/me";
    }
}
