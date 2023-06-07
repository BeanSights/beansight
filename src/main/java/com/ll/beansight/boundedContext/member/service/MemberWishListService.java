package com.ll.beansight.boundedContext.member.service;

import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.member.entity.MemberWishList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberWishListService {
    public RsData<MemberWishList> createMemberWishList() {
        return null;
    }
}
