package com.ll.beansight.boundedContext.cafeInfo.entity;


import com.ll.beansight.base.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString
@Entity
@Getter
public class CafeInfo extends BaseEntity {
    @Column(unique = true)
    private Long cafeId;
    private String cafeName;
    private String cafeAddress;
    private String cafePhoneNumber;
    private String cafeTag;


}
