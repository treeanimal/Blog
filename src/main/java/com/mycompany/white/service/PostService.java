package com.mycompany.white.service;

import com.mycompany.white.domain.dto.PostDto;
import com.mycompany.white.domain.entity.Category;
import com.mycompany.white.domain.entity.Post;

import java.util.List;

public interface PostService {

    void savePost(Long categoryId, String title, String content);

    List<PostDto> findAll();

    List<Post> findAllPost();

    Post findPost(Long postId);

    void updatePost(Long postId, PostDto postDto, Category category);

    void deletePost(Post post);

    Post findPostJoinCategory(Long postId);

    List<Post> findPostByCategoryName(String categoryName);
}
