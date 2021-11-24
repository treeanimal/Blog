package com.mycompany.white.service;

import com.mycompany.white.domain.dto.CategoryDto;
import com.mycompany.white.domain.dto.PostDto;
import com.mycompany.white.domain.entity.Category;
import com.mycompany.white.domain.entity.Post;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    @Transactional
    public void getCategory() {
        List<Post> allPost = postService.findAllPost();

//        for(Post p : allPost){
//            Category category = p.getCategory();
//            System.out.println("카테고리 이름 :: " + category.getName());
//            System.out.println("카테고리 번호 :: " + category.getOrderNum());
//        }
        List<PostDto> collect = allPost.stream()
                .map(m -> new PostDto(m)).collect(Collectors.toList());


        for (PostDto p : collect){
            System.out.println("제목 :: " + p.getTitle() +  "내용 : " + p.getContent());
            System.out.println("카테고리 :: " + p.getCategory());

        }


    }


}