package com.ll.beansight.boundedContext.member.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberWishList extends BaseEntity {
    private String wishListTitle;
    @ManyToOne
    private Member member;
    @OneToMany
    private List<CafeInfo> cafeList;
}
