package com.ll.beansight.boundedContext.tag.repository;

import com.ll.beansight.boundedContext.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
