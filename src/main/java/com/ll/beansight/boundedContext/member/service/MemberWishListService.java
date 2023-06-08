package com.ll.beansight.boundedContext.member.service;

import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.member.entity.Member;
import com.ll.beansight.boundedContext.member.entity.MemberWishList;
import com.ll.beansight.boundedContext.member.repository.MemberWishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return memberWishListRepository.findAllById(memberId);
    }
}
