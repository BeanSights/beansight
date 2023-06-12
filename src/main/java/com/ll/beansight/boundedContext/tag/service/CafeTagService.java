package com.ll.beansight.boundedContext.tag.service;

import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import com.ll.beansight.boundedContext.tag.repository.CafeTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeTagService {
    private final CafeTagRepository cafeTagRepository;
    public void add(List<CafeTag> cafeTags) {
        cafeTagRepository.saveAll(cafeTags);
    }

    public void delete(CafeInfo cafeInfo) {
        cafeTagRepository.deleteAllByCafeInfo(cafeInfo);
    }
}
