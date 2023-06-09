package com.ll.beansight.boundedContext.review.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.member.entity.Member;
import com.ll.beansight.boundedContext.search.entity.Cafe;
import com.ll.beansight.boundedContext.tag.entity.ReviewTag;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CafeReview extends BaseEntity {
    @ManyToOne
    private Member member;
    @Column(length = 100)
    private String content;
    @OneToMany(mappedBy = "cafeReview", cascade = CascadeType.REMOVE)
    private List<ReviewTag> reviewTags = new ArrayList<>();
    @ManyToOne
    @ToString.Exclude
    private CafeInfo cafeInfo;
}
