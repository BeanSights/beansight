package com.ll.beansight.boundedContext.member.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class MemberWishList extends BaseEntity {
    private String wishListTitle;
    @ManyToOne
    private Member member;
}
