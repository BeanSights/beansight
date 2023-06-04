package com.ll.beansight.boundedContext.tag.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.member.entity.Member;
import com.ll.beansight.boundedContext.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseEntity {
    private String tagName;
    @ManyToOne
    private Review review;

    @ManyToOne
    private CafeInfo cafeInfo;
}
