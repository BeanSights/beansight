package com.ll.beansight.boundedContext.tag.service;

import com.ll.beansight.boundedContext.tag.entity.MemberTag;
import com.ll.beansight.boundedContext.tag.repository.MemberTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberTagService {
    private final MemberTagRepository memberTagRepository;
    public void add(List<MemberTag> memberTags) {
        memberTagRepository.saveAll(memberTags);
    }

    public List<MemberTag> findByMemberId(Long memberId){
        return memberTagRepository.findByMemberId(memberId);
    }
}
