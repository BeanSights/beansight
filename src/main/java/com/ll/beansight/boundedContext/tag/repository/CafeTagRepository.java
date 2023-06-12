package com.ll.beansight.boundedContext.tag.repository;

import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeTagRepository extends JpaRepository<CafeTag, Long> {
    List<CafeTag> findAllByTag_TagId(Long tagId);
    boolean existsByTag_TagIdAndCafeInfo(Long tagId, CafeInfo cafeInfo);


    void deleteAllByCafeInfo(CafeInfo cafeInfo);
}
