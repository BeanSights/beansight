package com.ll.beansight.boundedContext.tag.repository;

import com.ll.beansight.boundedContext.tag.entity.MemberTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTagRepository extends JpaRepository<MemberTag, Long> {
}
