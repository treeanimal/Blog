package com.mycompany.white.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity{

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private int orderNum;


    @Builder
    public Category(String name, int orderNum) {
        this.name = name;
        this.orderNum = orderNum;
    }

    public void updateCategory(String name, int orderNum) {
        this.name = name;
        this.orderNum = orderNum;
    }
}
