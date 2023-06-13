package com.ll.beansight.boundedContext.search.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class SearchControllerTest {

    @Autowired
    private MockMvc mvc;

//    @Test
//    @DisplayName("가까운 카페를 찾는 경우 위도 경도가 있어야한다.")
//    @WithUserDetails("user1")
//    void t001() throws Exception {
//        // WHEN
//        ResultActions resultActions = mvc
//                .perform(get("/search/near-cafe?x=126.97890911337976&y=37.571150829509854"))
//                .andDo(print());
//
//        // THEN
//        resultActions
//                .andExpect(status().is2xxSuccessful());
//    }
//
//    @Test
//    @DisplayName("가까운 카페를 찾는 경우 위도 경도가 없어도 성공해야한다.")
//    @WithUserDetails("user1")
//    void t002() throws Exception {
//        // WHEN
//        ResultActions resultActions = mvc
//                .perform(get("/search/near-cafe"))
//                .andDo(print());
//
//        // THEN
//        resultActions
//                .andExpect(status().is2xxSuccessful());
//    }
//
//    @Test
//    @DisplayName("키워드로 카페를 찾는 경우 키워드도 있고 위도 경도가 있으면 성공한다.")
//    @WithUserDetails("user1")
//    void t003() throws Exception {
//        // WHEN
//        ResultActions resultActions = mvc
//                .perform(get("/search/keyword/스타벅스?x=126.97890911337976&y=37.571150829509854"))
//                .andDo(print());
//
//        // THEN
//        resultActions
//                .andExpect(status().is2xxSuccessful());
//    }
//
//    @Test
//    @DisplayName("키워드로 카페를 찾는 경우 키워드가 있지만, 위도가 경도가 없어도 성공한다.")
//    @WithUserDetails("user1")
//    void t004() throws Exception {
//        // WHEN
//        ResultActions resultActions = mvc
//                .perform(get("/search/keyword/스타벅스"))
//                .andDo(print());
//
//        // THEN
//        resultActions
//                .andExpect(status().is2xxSuccessful());
//    }
//
//    @Test
//    @DisplayName("키워드로 카페를 찾는 경우 키워드가 없으면 실패한다.")
//    @WithUserDetails("user1")
//    void t006() throws Exception {
//        // WHEN
//        ResultActions resultActions = mvc
//                .perform(get("/search/keyword"))
//                .andDo(print());
//
//        // THEN
//        resultActions
//                .andExpect(status().is4xxClientError());
//    }



}
