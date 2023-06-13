package com.ll.beansight.boundedContext.cafeInfo.repository;

import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfoWishList;
import com.ll.beansight.boundedContext.member.entity.MemberWishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CafeInfoWishListRepository extends JpaRepository<CafeInfoWishList, Long> {
    List<CafeInfoWishList> findByCafeInfoIdAndMemberId(Long cafeInfoId, Long id);

    boolean existsByCafeInfoAndMemberWishList(CafeInfo cafeInfo, MemberWishList memberWishList);
}
