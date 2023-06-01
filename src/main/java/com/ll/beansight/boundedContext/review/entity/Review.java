package com.ll.beansight.boundedContext.review.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.search.entity.Cafe;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity {
    private Long memberId;
    @Column(length = 100)
    private String content;
    @ManyToOne
    private Cafe cafe;
    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<Tag> tagList;
    @ManyToOne
    @ToString.Exclude
    private CafeInfo cafeInfo;
}
