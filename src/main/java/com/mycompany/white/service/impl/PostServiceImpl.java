package com.mycompany.white.service.impl;

import com.mycompany.white.domain.dto.PostDto;
import com.mycompany.white.domain.entity.Category;
import com.mycompany.white.domain.entity.Post;
import com.mycompany.white.repository.PostRepository;
import com.mycompany.white.service.CategoryService;
import com.mycompany.white.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;


    @Override
    public void savePost(Long categoryId,String title, String content) {
        Category category = categoryService.findCategory(categoryId);

        Post post = Post.createPost(title, content, category);
        postRepository.save(post);
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAllPost();
    }


    @Override
    public Post findPost(Long postId) {
        Post findPost = postRepository.findById(postId).orElse(null);
        if (findPost == null) throw new NullPointerException("유효한 접근이 아니거나 해당 게시물이 삭제되었습니다.");
        else return findPost;
    }

    @Override
    @Transactional
    public void updatePost(Long postId, PostDto postDto, Category category) {
        Post findPost = postRepository.findById(postId).orElse(null);
        if (findPost == null) throw new NullPointerException("유효한 접근이 아니거나 해당 게시글이 삭제되었습니다.");
        else {
            findPost.updatePost(postDto.getTitle(), postDto.getContent(), category);
        }
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public Post findPostJoinCategory(Long postId) {
        Post findPost = postRepository.findPostJoinCategory(postId).orElse(null);
        if (findPost == null) throw new NullPointerException("유효한 접근이 아니거나 해당 게시글이 삭제되었습니다.");
        else return findPost;
    }

    @Override
    public List<Post> findPostByCategoryName(String categoryName) {
        return postRepository.findPostByCategoryName(categoryName);
    }
}
