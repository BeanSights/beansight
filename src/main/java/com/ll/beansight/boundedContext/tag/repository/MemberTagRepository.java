package com.ll.beansight.boundedContext.tag.repository;

import com.ll.beansight.boundedContext.tag.entity.MemberTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberTagRepository extends JpaRepository<MemberTag, Long> {
    List<MemberTag> findByMemberId(Long memberId);
}
