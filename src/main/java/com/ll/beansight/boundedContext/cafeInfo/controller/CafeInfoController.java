package com.ll.beansight.boundedContext.cafeInfo.controller;

import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.service.CafeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cafeInfo")
public class CafeInfoController {
    private final CafeInfoService cafeInfoService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String showInfo(Model model, @RequestParam(defaultValue = "126.97890911337976") double x,
                           @RequestParam(defaultValue = "37.571150829509854") double y){
        DocumentDTO cafeInfoResponse = cafeInfoService.search(x, y);
        Optional<CafeInfo> cafeInfo = cafeInfoService.findBycafeId(cafeInfoResponse.getId());
        model.addAttribute("cafeInfo", cafeInfoResponse);




        return "usr/cafeInfo/showInfo";
    }
}
