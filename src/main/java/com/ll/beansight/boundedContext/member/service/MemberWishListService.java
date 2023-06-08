package com.ll.beansight.boundedContext.member.service;

import com.ll.beansight.base.rq.Rq;
import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.member.entity.Member;
import com.ll.beansight.boundedContext.member.entity.MemberWishList;
import com.ll.beansight.boundedContext.member.repository.MemberWishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberWishListService {
    private final MemberWishListRepository memberWishListRepository;
    public RsData<MemberWishList> createMemberWishList(Member member, String content) {
        MemberWishList memberWishList = MemberWishList.builder()
                .wishListTitle(content)
                .member(member)
                .build();
        memberWishListRepository.save(memberWishList);
        return RsData.of("S-1", "찜목록이 생성되었습니다.", memberWishList);
    }

    public List<MemberWishList> getMemberWishLists(Long memberId) {
        return memberWishListRepository.findAllByMemberId(memberId);
    }

    public RsData<MemberWishList> addWishList(Member member, CafeInfo cafeInfo, String wish) {
        Long memberId = member.getId();
        Optional<MemberWishList> memberWishList = memberWishListRepository.findByMemberIdAndWishListTitle(memberId, wish);
        if (memberWishList.isEmpty()) {
            return RsData.of("F-1", "찜목록이 존재하지 않습니다.");
        }
        memberWishList.get().getCafeList().add(cafeInfo);
        return RsData.of("S-2", "찜목록에 카페가 추가되었습니다.", memberWishList.get());
    }
}
