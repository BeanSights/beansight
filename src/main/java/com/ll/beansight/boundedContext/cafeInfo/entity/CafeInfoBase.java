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

    long tagsCountByTypeCode100;
    long tagsCountByTypeCode101;
    long tagsCountByTypeCode102;
    long tagsCountByTypeCode103;
    long tagsCountByTypeCode200;
    long tagsCountByTypeCode201;
    long tagsCountByTypeCode202;
    long tagsCountByTypeCode203;
    long tagsCountByTypeCode300;
    long tagsCountByTypeCode301;
    long tagsCountByTypeCode400;
    long tagsCountByTypeCode401;


}
