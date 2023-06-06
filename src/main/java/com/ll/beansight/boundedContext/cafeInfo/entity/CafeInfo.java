package com.ll.beansight.boundedContext.cafeInfo.entity;


import com.ll.beansight.boundedContext.review.entity.CafeReview;
import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
    @Builder.Default
    private List<CafeTag> cafeTags = new ArrayList<>();

    @OneToMany(mappedBy = "cafeInfo", cascade = {CascadeType.ALL})
    @OrderBy("id desc")
    @Builder.Default
    private List<CafeReview> cafeReview = new ArrayList<>(); //리뷰 내용

    public void increaseTagCount(int tagTypeCode) {
        if (tagTypeCode == 1) tagsCountByTypeCode100++;
        if (tagTypeCode == 2) tagsCountByTypeCode101++;
        if (tagTypeCode == 3) tagsCountByTypeCode102++;
        if (tagTypeCode == 4) tagsCountByTypeCode103++;
        if (tagTypeCode == 5) tagsCountByTypeCode200++;
        if (tagTypeCode == 6) tagsCountByTypeCode201++;
        if (tagTypeCode == 7) tagsCountByTypeCode202++;
        if (tagTypeCode == 8) tagsCountByTypeCode203++;
        if (tagTypeCode == 9) tagsCountByTypeCode300++;
        if (tagTypeCode == 10) tagsCountByTypeCode301++;
        if (tagTypeCode == 11) tagsCountByTypeCode400++;
        if (tagTypeCode == 12) tagsCountByTypeCode401++;
    }

    public void decreaseTagCount(int tagTypeCode) {
        if (tagTypeCode == 1) tagsCountByTypeCode100--;
        if (tagTypeCode == 2) tagsCountByTypeCode101--;
        if (tagTypeCode == 3) tagsCountByTypeCode102--;
        if (tagTypeCode == 4) tagsCountByTypeCode103--;
        if (tagTypeCode == 5) tagsCountByTypeCode200--;
        if (tagTypeCode == 6) tagsCountByTypeCode201--;
        if (tagTypeCode == 7) tagsCountByTypeCode202--;
        if (tagTypeCode == 8) tagsCountByTypeCode203--;
        if (tagTypeCode == 9) tagsCountByTypeCode300--;
        if (tagTypeCode == 10) tagsCountByTypeCode301--;
        if (tagTypeCode == 11) tagsCountByTypeCode400--;
        if (tagTypeCode == 12) tagsCountByTypeCode401--;
    }

}