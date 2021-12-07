package com.mycompany.white.controller;

import com.mycompany.white.domain.dto.CategoryDto;
import com.mycompany.white.domain.dto.PaginationBean;
import com.mycompany.white.domain.dto.PostDto;
import com.mycompany.white.domain.entity.Post;
import com.mycompany.white.service.CategoryService;
import com.mycompany.white.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping("/")
    public String home(@RequestParam(name = "category", required = false, defaultValue = "-1")Long categoryId,
                       @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){


        if (categoryId == null || categoryId == -1){
            List<PostDto> findPosts = postService.findAll(pageable);
            List<Post> findAllPost = postService.findAllPost();
            PaginationBean page = new PaginationBean(findAllPost.size(), pageable.getPageNumber(), 10, 10);

            model.addAttribute("categoryId", categoryId);
            model.addAttribute("size", findAllPost.size());
            model.addAttribute("page", page);
            model.addAttribute("posts", findPosts);
        }else{
            List<PostDto> findPosts = postService.findPostByCategoryId(categoryId, pageable);
            List<Post> findAllPost = postService.findAllPost();
            PaginationBean page = new PaginationBean(findAllPost.size(), pageable.getPageNumber(), 10, 10);

            model.addAttribute("page", page);
            model.addAttribute("posts", findPosts);
        }

        List<CategoryDto> findCategoryDtos = categoryService.findAllCategoryJoinPost();

        model.addAttribute("categories", findCategoryDtos);
        return "home";
    }
}
