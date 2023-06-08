package com.ll.beansight.boundedContext.cafeInfo.controller;

import com.ll.beansight.base.api.dto.DocumentDTO;
import com.ll.beansight.base.rq.Rq;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.service.CafeInfoService;
import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    private final Rq rq;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String showInfo(Model model, @RequestParam(defaultValue = "126.97890911337976") double x,
                           @RequestParam(defaultValue = "37.571150829509854") double y){
        CafeInfo cafeInfoResponse = cafeInfoService.search(x, y);

        model.addAttribute("cafeInfo", cafeInfoResponse);

        return "usr/cafeInfo/showInfo";
    }

    @AllArgsConstructor
    @Getter
    public static class WishForm {
        @NotNull
        private final List<String> cafeList;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public String showInfo(@Valid WishForm wishForm ,@RequestParam(defaultValue = "126.97890911337976") double x,
                           @RequestParam(defaultValue = "37.571150829509854") double y){
        CafeInfo cafeInfoResponse = cafeInfoService.search(x, y);
        String a = wishForm.cafeList.get(1);




        return rq.redirectWithMsg("/cafeInfo?x="+x+"&y="+y, "good");
    }
}
