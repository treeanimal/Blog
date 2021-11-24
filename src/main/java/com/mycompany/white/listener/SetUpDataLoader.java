package com.mycompany.white.listener;

import com.mycompany.white.domain.entity.Category;
import com.mycompany.white.domain.entity.Post;
import com.mycompany.white.service.CategoryService;
import com.mycompany.white.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Category category1 = Category.builder()
                .name("JAVA")
                .orderNum(1)
                .build();
        Category category2 = Category.builder()
                .name("공지사항")
                .orderNum(2)
                .build();
        Category category3 = Category.builder()
                .name("JPA")
                .orderNum(3)
                .build();

        categoryService.saveCategory(category1);
        categoryService.saveCategory(category2);
        categoryService.saveCategory(category3);

        Post post = Post.createPost("제목입니다.", "내용입니다.", category1);

        postService.savePost(post.getCategory().getId(), post.getTitle(), post.getContent());
    }
}
