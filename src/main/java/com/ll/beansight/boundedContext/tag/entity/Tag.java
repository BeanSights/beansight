package com.ll.beansight.boundedContext.tag.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.cafeInfo.entity.CafeInfo;
import com.ll.beansight.boundedContext.review.entity.CafeReview;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseEntity {
    private String tagCode;
    @ManyToOne
    private CafeReview cafeReview;
    @ManyToOne
    private CafeInfo cafeInfo;

    public String getTagCodeName(){
        return switch (tagCode){
            case "100" -> "대화하기 좋은";
            case "101" -> "깔끔한";
            case "102" -> "인스타 감성";
            case "103" -> "스터디";
            case "200" -> "주차장";
            case "201" -> "콘센트";
            case "202" -> "넓은 매장";
            case "203" -> "깨끗한 화장실";
            case "300" -> "착한 가격";
            case "301" -> "디저트";
            case "400" -> "쿠폰 적립";
            case "401" -> "친절한 매장";
            default -> "";
        };
    }
}
