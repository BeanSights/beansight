package com.ll.beansight.boundedContext.member.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.search.entity.Cafe;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class WishList extends BaseEntity {
    private Long memberWishListId;
    @OneToMany
    private List<CafeInfo> cafeList;
}
