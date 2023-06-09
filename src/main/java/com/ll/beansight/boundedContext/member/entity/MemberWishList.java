package com.ll.beansight.boundedContext.member.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfoWishList;
import com.ll.beansight.boundedContext.search.entity.Cafe;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberWishList extends BaseEntity {
    private String wishListTitle;
    @ManyToOne
    private Member member;
    @OneToMany(mappedBy = "memberWishList", cascade = CascadeType.ALL)
    private List<CafeInfoWishList> cafeInfoWishLists;


}
