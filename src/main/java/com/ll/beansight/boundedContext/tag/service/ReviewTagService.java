package com.ll.beansight.boundedContext.tag.service;

import com.ll.beansight.boundedContext.tag.entity.ReviewTag;
import com.ll.beansight.boundedContext.tag.repository.ReviewTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewTagService {
    private final ReviewTagRepository reviewTagRepository;
    public void add(List<ReviewTag> reviewTags) {
        reviewTagRepository.saveAll(reviewTags);
    }
}
