package com.mycompany.white.domain.dto;

import com.mycompany.white.domain.entity.Category;
import com.mycompany.white.domain.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private int orderNum;

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
}
