package com.ll.beansight.boundedContext.member.repository;

import com.ll.beansight.boundedContext.member.entity.MemberWishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberWishListRepository extends JpaRepository<MemberWishList, Long> {
    List<MemberWishList> findAllByMemberId(Long memberId);
}
