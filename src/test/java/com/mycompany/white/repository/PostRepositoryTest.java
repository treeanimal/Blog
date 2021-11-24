package com.mycompany.white.repository;

import com.mycompany.white.service.CategoryService;
import com.mycompany.white.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;


}