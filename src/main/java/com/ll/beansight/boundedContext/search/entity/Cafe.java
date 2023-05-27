package com.ll.beansight.boundedContext.search.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

}
