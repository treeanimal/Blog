package com.mycompany.white.controller;

import com.mycompany.white.domain.dto.CategoryDto;
import com.mycompany.white.domain.dto.PostDto;
import com.mycompany.white.domain.entity.Post;
import com.mycompany.white.service.CategoryService;
import com.mycompany.white.service.PostService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping("/")
    public String home(Model model){

        List<CategoryDto> findCategoryDtos = categoryService.findAllCategoryJoinPost();
        List<PostDto> findPosts = postService.findAll();

//        List<PostDto> postDtoList = findPosts.stream().map(p -> PostDto.createPostDto(p)).collect(Collectors.toList());

//        List<PostDto> postDtos = findPosts.stream().map(p -> new PostDto(p)).collect(Collectors.toList());

        model.addAttribute("posts", findPosts);
        model.addAttribute("categories", findCategoryDtos);
        return "home";
    }
}
