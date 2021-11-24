package com.mycompany.white.service;

import com.mycompany.white.domain.dto.CategoryDto;
import com.mycompany.white.domain.entity.Category;

import java.util.List;

public interface CategoryService {

    void saveCategory(Category category);

    List<Category> findAllCategory();

    Category findCategory(Long categoryId);

    void updateCategory(Long categoryId, Category category);

    void deleteCategory(Category category);

}
