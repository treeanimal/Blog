package com.mycompany.white.service.impl;

import com.mycompany.white.domain.dto.PaginationBean;
import com.mycompany.white.domain.dto.PostDto;
import com.mycompany.white.domain.entity.Category;
import com.mycompany.white.domain.entity.Post;
import com.mycompany.white.repository.PostRepository;
import com.mycompany.white.service.CategoryService;
import com.mycompany.white.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<PostDto> findAll(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        postRepository.findAll(pageable);

        List<PostDto> postDtos = new ArrayList<>();
        for (Post p : posts){
            PostDto postDto = new PostDto();
            postDto.setId(p.getId());
            postDto.setTitle(p.getTitle());
            postDto.setContent( p.getContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
            postDto.setCreatedDate(p.getCreatedDate());
            postDtos.add(postDto);
        }


        return postDtos;
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
    public List<PostDto> findPostByCategoryName(String categoryName, Pageable pageable) {

        Page<Post> findPosts = postRepository.findPostByCategoryName(categoryName, pageable);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post p : findPosts){
            PostDto postDto = new PostDto();
            postDto.setTitle(p.getTitle());
            postDto.setContent(p.getContent());
            postDto.setCreatedDate(p.getCreatedDate());

            postDtos.add(postDto);

        }
        return postDtos;
    }

    @Override
    public List<PostDto> findPostByCategoryId(Long categoryId, Pageable pageable) {
        Page<Post> findPosts = postRepository.findPostByCategoryId(categoryId, pageable);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post p : findPosts){
            PostDto postDto = new PostDto();
            postDto.setTitle(p.getTitle());
            postDto.setContent(p.getContent());
            postDto.setCreatedDate(p.getCreatedDate());

            postDtos.add(postDto);

        }
        return postDtos;
    }


}
