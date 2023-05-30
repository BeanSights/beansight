package com.ll.beansight.boundedContext.cafeInfo.entity;


import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.review.entity.Review;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CafeInfo extends BaseEntity {


    private Long cafeId;
    private double x;
    private double y;
    private String cafeName;
    private String cafeAddress;
    private String cafePhoneNumber;


    @OneToMany(mappedBy = "cafeInfo", cascade = {CascadeType.ALL})
    @OrderBy("id desc")
    @Builder.Default
    private List<Tag> cafeTag = new ArrayList<>();

    @OneToMany(mappedBy = "cafeInfo", cascade = {CascadeType.ALL})
    @OrderBy("id desc")
    @Builder.Default
    private List<Review> cafeReview = new ArrayList<>();

}
