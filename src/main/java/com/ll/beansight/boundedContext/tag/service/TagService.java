package com.ll.beansight.boundedContext.tag.service;

import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import com.ll.beansight.boundedContext.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {
    private final TagRepository tagRepository;
    public RsData<Tag> setTag(String tagCode) {
        Tag tag = Tag
                .builder()
                .tagName(tagCode)
                .build();

        tagRepository.save(tag);

        return RsData.of("S-1", "태그가 생성되었습니다.", tag);
    }
}
