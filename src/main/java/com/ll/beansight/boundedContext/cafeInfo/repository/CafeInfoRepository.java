package com.ll.beansight.boundedContext.cafeInfo.repository;

import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//주 키 타입 카페 장소 id
public interface CafeInfoRepository extends JpaRepository<CafeInfo, Long> {
    Optional<CafeInfo> findByCafeId(Long cafeId);
}
