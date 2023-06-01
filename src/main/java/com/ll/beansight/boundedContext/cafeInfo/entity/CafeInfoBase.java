package com.ll.beansight.boundedContext.cafeInfo.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public abstract class CafeInfoBase extends BaseEntity {

    long tagsCountByTagTypeCode1;
    long tagsCountByTagTypeCode2;
    long tagsCountByTagTypeCode3;
    long tagsCountByTagTypeCode4;
    long tagsCountByTagTypeCode5;
    long tagsCountByTagTypeCode6;
    long tagsCountByTagTypeCode7;
    long tagsCountByTagTypeCode8;
    long tagsCountByTagTypeCode9;
    long tagsCountByTagTypeCode10;
    long tagsCountByTagTypeCode11;
    long tagsCountByTagTypeCode12;


}
