package com.ll.beansight.boundedContext.cafeInfo.repository;

import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfoWishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CafeInfoWishListRepository extends JpaRepository<CafeInfoWishList, Long> {
    Optional<CafeInfoWishList> findByCafeInfoIdAndMemberId(Long cafeInfoId, Long id);
}
