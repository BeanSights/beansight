package com.ll.beansight.boundedContext.tag.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.review.entity.CafeReview;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseEntity {
    private String tagName;
    @ManyToOne
    private CafeReview cafeReview;
    @ManyToOne
    private CafeInfo cafeInfo;
}
