package com.ll.beansight.boundedContext.tag.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import lombok.*;


@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    private Long tagId;
    private String tagName;
}
