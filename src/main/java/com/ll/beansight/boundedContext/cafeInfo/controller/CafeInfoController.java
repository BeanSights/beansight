package com.ll.beansight.boundedContext.cafeInfo.controller;

import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.service.CafeInfoService;
import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cafeInfo")
public class CafeInfoController {
    private final CafeInfoService cafeInfoService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String showInfo(Model model, @RequestParam(defaultValue = "126.97890911337976") double x,
                           @RequestParam(defaultValue = "37.571150829509854") double y){
        CafeInfo cafeInfoResponse = cafeInfoService.search(x, y);

        model.addAttribute("cafeInfo", cafeInfoResponse);

        return "usr/cafeInfo/showInfo";
    }
}
