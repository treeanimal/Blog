package com.mycompany.white.controller;

import com.mycompany.white.domain.dto.CategoryDto;
import com.mycompany.white.domain.entity.Category;
import com.mycompany.white.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @GetMapping("/admin/category")
    public String category(Model model) {
        List<Category> findCategories = categoryService.findAllCategory();
        List<CategoryDto> categoryDtos = findCategories.stream().map(c -> new CategoryDto(c)).collect(Collectors.toList());

        model.addAttribute("categories", categoryDtos);
        return "admin/category/adminCategoryList";
    }

    @GetMapping("/admin/category/new")
    public String categoryForm(Model model) {
        model.addAttribute("categoryForm", new CategoryDto());
        return "admin/category/adminCategoryForm";
    }

    @PostMapping("/admin/category/new")
    public String saveCategory(CategoryDto categoryDto) {

        Category category = Category.builder()
                .name(categoryDto.getName())
                .orderNum(categoryDto.getOrderNum())
                .build();

        categoryService.saveCategory(category);

        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/update/{categoryId}")
    public String updateCategoryForm(@PathVariable Long categoryId, Model model) {

        Category findCategory = categoryService.findCategory(categoryId);
        CategoryDto categoryDto = modelMapper.map(findCategory, CategoryDto.class);

        model.addAttribute("categoryForm", categoryDto);
        return "admin/category/adminCategoryUpdateForm";
    }

    @PostMapping("/admin/category/update/{categoryId}")
    public String updateCategory(@PathVariable Long categoryId, CategoryDto categoryDto) {

        Category category = Category.builder()
                .name(categoryDto.getName())
                .orderNum(categoryDto.getOrderNum())
                .build();

        categoryService.updateCategory(categoryId, category);
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/delete/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        Category category = categoryService.findCategory(categoryId);

        categoryService.deleteCategory(category);
        return "redirect:/admin/category";
    }
}
