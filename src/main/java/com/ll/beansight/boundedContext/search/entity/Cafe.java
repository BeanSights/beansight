package com.ll.beansight.boundedContext.search.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.review.entity.Review;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cafe extends BaseEntity {

    private Long kakaoCafeId;
    private double cafeX;
    private double cafeY;
    private String cafeName;
    @OneToMany
    private List<Review> reviewList;
    @OneToMany
    private List<Tag> tagList;

}
