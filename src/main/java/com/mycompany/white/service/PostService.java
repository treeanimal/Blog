package com.mycompany.white.service;

import com.mycompany.white.domain.dto.PaginationBean;
import com.mycompany.white.domain.dto.PostDto;
import com.mycompany.white.domain.entity.Category;
import com.mycompany.white.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    void savePost(Long categoryId, String title, String content);

    List<PostDto> findAll(Pageable pageable);

    List<Post> findAllPost();

    Post findPost(Long postId);

    void updatePost(Long postId, PostDto postDto, Category category);

    void deletePost(Post post);

    Post findPostJoinCategory(Long postId);

    List<PostDto> findPostByCategoryName(String categoryName, Pageable pageable);

    List<PostDto> findPostByCategoryId(Long categoryId, Pageable pageable);

}
