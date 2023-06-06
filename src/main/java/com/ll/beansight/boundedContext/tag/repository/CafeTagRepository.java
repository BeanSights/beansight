package com.ll.beansight.boundedContext.tag.repository;

import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CafeTagRepository extends JpaRepository<CafeTag, Long> {
    List<CafeTag> findAllByTagId_TagId(Long tagId);
    boolean existsByTagId_TagIdAndCafeInfo(Long tagId, CafeInfo cafeInfo);

}
