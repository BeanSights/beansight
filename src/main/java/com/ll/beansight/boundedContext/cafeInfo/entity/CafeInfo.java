package com.ll.beansight.boundedContext.cafeInfo.entity;


import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.review.entity.CafeReview;
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
public class CafeInfo extends CafeInfoBase {

    @Column(unique = true)
    private Long cafeId;
    private double x;
    private double y;
    private String cafeName;
    private String cafeAddress;
    private String cafePhoneNumber;


    @OneToMany(mappedBy = "cafeInfo", cascade = {CascadeType.ALL})
    @OrderBy("id desc")
    @Builder.Default
    private List<Tag> cafeTag = new ArrayList<>(); //태그 종류

    @OneToMany(mappedBy = "cafeInfo", cascade = {CascadeType.ALL})
    @OrderBy("id desc")
    @Builder.Default
    private List<CafeReview> cafeReview = new ArrayList<>(); //리뷰 내용

    public void increaseTagCount(int tagTypeCode) {
        if (tagTypeCode == 1) tagsCountByTagTypeCode1++;
        if (tagTypeCode == 2) tagsCountByTagTypeCode2++;
        if (tagTypeCode == 3) tagsCountByTagTypeCode3++;
        if (tagTypeCode == 4) tagsCountByTagTypeCode4++;
        if (tagTypeCode == 5) tagsCountByTagTypeCode5++;
        if (tagTypeCode == 6) tagsCountByTagTypeCode6++;
        if (tagTypeCode == 7) tagsCountByTagTypeCode7++;
        if (tagTypeCode == 8) tagsCountByTagTypeCode8++;
        if (tagTypeCode == 9) tagsCountByTagTypeCode9++;
        if (tagTypeCode == 10) tagsCountByTagTypeCode10++;
        if (tagTypeCode == 11) tagsCountByTagTypeCode11++;
        if (tagTypeCode == 12) tagsCountByTagTypeCode12++;
    }

    public void decreaseTagCount(int tagTypeCode) {
        if (tagTypeCode == 1) tagsCountByTagTypeCode1--;
        if (tagTypeCode == 2) tagsCountByTagTypeCode2--;
        if (tagTypeCode == 3) tagsCountByTagTypeCode3--;
        if (tagTypeCode == 4) tagsCountByTagTypeCode4--;
        if (tagTypeCode == 5) tagsCountByTagTypeCode5--;
        if (tagTypeCode == 6) tagsCountByTagTypeCode6--;
        if (tagTypeCode == 7) tagsCountByTagTypeCode7--;
        if (tagTypeCode == 8) tagsCountByTagTypeCode8--;
        if (tagTypeCode == 9) tagsCountByTagTypeCode9--;
        if (tagTypeCode == 10) tagsCountByTagTypeCode10--;
        if (tagTypeCode == 11) tagsCountByTagTypeCode11--;
        if (tagTypeCode == 12) tagsCountByTagTypeCode12--;
    }

}