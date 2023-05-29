package com.ll.beansight.boundedContext.cafeInfo.controller;

import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.service.CafeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cafeInfo")
public class CafeInfoController {
    private final CafeInfoService cafeInfoService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{cafeId}")
    public String showInfo(@PathVariable Long cafeId) {
        Optional<CafeInfo> cafeInfo = cafeInfoService.findBycafeId(cafeId);
        //TODO: cafeId에 해당하는 정보 가져오기


        return "usr/cafeInfo/showInfo";
    }
}
