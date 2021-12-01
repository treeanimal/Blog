package com.mycompany.white.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity{

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 10000000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    //== 연관관계 메서드 ==//
//    public void changeCategory(Category category){
//        this.category = category;
//        category.getPostList().add(this);
//    }

    public Post(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    //== 생성 메서드 ==//
    public static Post createPost(String title, String content, Category category){
        return new Post(title, content, category);
    }


    public void updatePost(String title, String content, Category category){
        this.title = title;
        this.content = content;
        this.category = category;
    }

}
