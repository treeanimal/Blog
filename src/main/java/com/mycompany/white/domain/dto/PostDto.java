package com.mycompany.white.domain.dto;

import com.mycompany.white.domain.entity.Category;
import com.mycompany.white.domain.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    @Column(name = "post_id")
    private Long id;
    private String title;
    private String content;
    private CategoryDto category;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Autowired
    private ModelMapper modelMapper;

    public void setCategory(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setOrderNum(category.getOrderNum());
        this.category = categoryDto;
    }

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
        setCategory(post.getCategory());
    }

    public static PostDto createPostDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(postDto.getTitle());
        postDto.setContent(postDto.getContent());
        postDto.setCreatedDate(post.getCreatedDate());

        return postDto;
    }


}
