package com.ll.beansight.boundedContext.cafeInfo.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.member.entity.MemberWishList;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CafeInfoWishList extends BaseEntity {
    @ManyToOne
    private CafeInfo cafeInfo;
    @ManyToOne
    private MemberWishList memberWishList;
}
