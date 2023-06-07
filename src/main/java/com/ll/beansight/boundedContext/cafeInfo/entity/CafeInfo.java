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

    public void increaseTagCount(Long tagTypeCode) {
        if (tagTypeCode == 100) tagsCountByTypeCode100++;
        if (tagTypeCode == 101) tagsCountByTypeCode101++;
        if (tagTypeCode == 102) tagsCountByTypeCode102++;
        if (tagTypeCode == 103) tagsCountByTypeCode103++;
        if (tagTypeCode == 200) tagsCountByTypeCode200++;
        if (tagTypeCode == 201) tagsCountByTypeCode201++;
        if (tagTypeCode == 202) tagsCountByTypeCode202++;
        if (tagTypeCode == 203) tagsCountByTypeCode203++;
        if (tagTypeCode == 300) tagsCountByTypeCode300++;
        if (tagTypeCode == 301) tagsCountByTypeCode301++;
        if (tagTypeCode == 400) tagsCountByTypeCode400++;
        if (tagTypeCode == 401) tagsCountByTypeCode401++;
    }

    public void decreaseTagCount(Long tagTypeCode) {
        if (tagTypeCode == 100) tagsCountByTypeCode100--;
        if (tagTypeCode == 101) tagsCountByTypeCode101--;
        if (tagTypeCode == 102) tagsCountByTypeCode102--;
        if (tagTypeCode == 103) tagsCountByTypeCode103--;
        if (tagTypeCode == 200) tagsCountByTypeCode200--;
        if (tagTypeCode == 201) tagsCountByTypeCode201--;
        if (tagTypeCode == 202) tagsCountByTypeCode202--;
        if (tagTypeCode == 203) tagsCountByTypeCode203--;
        if (tagTypeCode == 300) tagsCountByTypeCode300--;
        if (tagTypeCode == 301) tagsCountByTypeCode301--;
        if (tagTypeCode == 400) tagsCountByTypeCode400--;
        if (tagTypeCode == 401) tagsCountByTypeCode401--;
    }

}