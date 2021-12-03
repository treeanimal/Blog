package com.mycompany.white.domain.dto;

import com.mycompany.white.domain.entity.Category;
import com.mycompany.white.domain.entity.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    @Column(name = "category_id")
    private Long id;
    private String name;
    private int orderNum;
    private Long postNum;

    //== 연관관계 메서드 ==//
//    public void addPost(Post post){
//        PostDto postDto = new PostDto();
//        postDto.setTitle(post.getTitle());
//        postDto.setContent(post.getContent());
//        this.postList.add(postDto);
//    }

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.orderNum = category.getOrderNum();

    }

    public CategoryDto(Long id, String name, int orderNum, Long postNum) {
        this.id = id;
        this.name = name;
        this.orderNum = orderNum;
        this.postNum = postNum;
    }
}
