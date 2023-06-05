package com.ll.beansight.boundedContext.tag.service;

import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import com.ll.beansight.boundedContext.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {
    private final TagRepository tagRepository;
    public RsData<Tag> setTag(Long tagId, String tagName) {
        Tag tag = Tag
                .builder()
                .tagId(tagId)
                .tagName(tagName)
                .build();

        tagRepository.save(tag);

        return RsData.of("S-1", "태그가 생성되었습니다.", tag);
    }

    public List<Tag> getTagList() {
        return tagRepository.findAll();
    }
}
