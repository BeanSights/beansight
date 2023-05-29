package com.ll.beansight.boundedContext.cafeInfo.service;

import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.repository.CafeInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeInfoService {
    private final CafeInfoRepository cafeInfoRepository;

    public Optional<CafeInfo> findBycafeId(Long cafeId) {

        return cafeInfoRepository.findByCafeId(cafeId);
    }
}
