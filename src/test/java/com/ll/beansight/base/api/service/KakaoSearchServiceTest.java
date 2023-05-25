package com.ll.beansight.base.api.service;

import com.ll.beansight.base.api.dto.KakaoApiResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class KakaoSearchServiceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private KakaoSearchService kakaoSearchService;

    @Test
    @DisplayName("모든값이 있다면 document 정상적으로 반환")
    void t1() throws Exception {
        // Given
        double longitude = 126.95803386590158;
        double latitude = 37.51766013568054;

        // When
        KakaoApiResponseDTO kakaoApiResponseDTO = kakaoSearchService.requestSearch(longitude, latitude);

        // Then
        assertThat(kakaoApiResponseDTO.getDocumentDTOList().size()).isNotNull();
    }

    @Test
    @DisplayName("하나라도 값을 안넣으면 null 반환")
    void t2() throws Exception {
        // Given
        double longitude = Double.parseDouble(null);
        double latitude = 37.5960650456809;

        // When
        KakaoApiResponseDTO kakaoApiResponseDTO = kakaoSearchService.requestSearch(longitude, latitude);

        // Then
        assertThat(kakaoApiResponseDTO).isNull();
    }
}
