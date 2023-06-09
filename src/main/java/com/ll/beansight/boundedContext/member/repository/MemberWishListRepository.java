package com.ll.beansight.boundedContext.member.repository;

import com.ll.beansight.boundedContext.member.entity.Member;
import com.ll.beansight.boundedContext.member.entity.MemberWishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberWishListRepository extends JpaRepository<MemberWishList, Long> {
    List<MemberWishList> findAllByMemberId(Long memberId);

    Optional<MemberWishList> findByMemberIdAndWishListTitle(Long memberId, String wish);

    Optional<MemberWishList> findByMemberIdAndId(Long id, Long wishListId);

}
