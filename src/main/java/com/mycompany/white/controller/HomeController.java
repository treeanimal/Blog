package com.mycompany.white.controller;

import com.mycompany.white.domain.dto.CategoryDto;
import com.mycompany.white.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model){

        List<CategoryDto> findCategoryDtos = categoryService.findAllCategoryJoinPost();

        model.addAttribute("categories", findCategoryDtos);
        return "home";
    }
}
