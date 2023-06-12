package com.ll.beansight.boundedContext.cafeInfo.entity;


import com.ll.beansight.boundedContext.member.entity.MemberWishList;
import com.ll.beansight.boundedContext.review.entity.CafeReview;
import com.ll.beansight.boundedContext.tag.entity.CafeTag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

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

    public Map<Long, Long> getTagsCountByTypeCode() {
        Map<Long, Long> map = new HashMap<>();
        if (tagsCountByTypeCode100 > 0) map.put(100L, tagsCountByTypeCode100);
        if (tagsCountByTypeCode101 > 0) map.put(101L, tagsCountByTypeCode101);
        if (tagsCountByTypeCode102 > 0) map.put(102L, tagsCountByTypeCode102);
        if (tagsCountByTypeCode103 > 0) map.put(103L, tagsCountByTypeCode103);
        if (tagsCountByTypeCode200 > 0) map.put(200L, tagsCountByTypeCode200);
        if (tagsCountByTypeCode201 > 0) map.put(201L, tagsCountByTypeCode201);
        if (tagsCountByTypeCode202 > 0) map.put(202L, tagsCountByTypeCode202);
        if (tagsCountByTypeCode203 > 0) map.put(203L, tagsCountByTypeCode203);
        if (tagsCountByTypeCode300 > 0) map.put(300L, tagsCountByTypeCode300);
        if (tagsCountByTypeCode301 > 0) map.put(301L, tagsCountByTypeCode301);
        if (tagsCountByTypeCode400 > 0) map.put(400L, tagsCountByTypeCode400);
        if (tagsCountByTypeCode401 > 0) map.put(401L, tagsCountByTypeCode401);
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeInfo cafeInfo = (CafeInfo) o;
        return cafeId.equals(cafeInfo.cafeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeId);
    }
}