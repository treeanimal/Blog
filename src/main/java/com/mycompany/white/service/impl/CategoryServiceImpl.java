package com.mycompany.white.service.impl;

import com.mycompany.white.domain.dto.CategoryDto;
import com.mycompany.white.domain.entity.Category;
import com.mycompany.white.domain.entity.Post;
import com.mycompany.white.exception.ExistPostException;
import com.mycompany.white.exception.NullCategoryException;
import com.mycompany.white.repository.CategoryRepository;
import com.mycompany.white.repository.PostRepository;
import com.mycompany.white.service.CategoryService;
import com.mycompany.white.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "orderNum"));
    }

    @Override
    public Category findCategory(Long categoryId) {
        Category findCategory = categoryRepository.findById(categoryId).orElse(null);
        if (findCategory == null) throw new NullCategoryException("유효한 접근이 아니거나 해당 카테고리가 삭제되었습니다.");
        else return findCategory;
    }

    @Override
    @Transactional
    public void updateCategory(Long categoryId, Category category) {
        Category findCategory = categoryRepository.findById(categoryId).orElse(null);

        if (findCategory == null) throw new NullCategoryException("유효한 접근이 아니거나 해당 카테고리가 삭제되었습니다.");
        else{
            findCategory.updateCategory(category.getName(), category.getOrderNum());
        }
    }

    @Override
    public void deleteCategory(Category category) {
        List<Post> countPost = postRepository.findPostByCategoryName(category.getName());
        if (countPost.size() > 0 ) throw new ExistPostException("해당 카테고리에 게시글이 존재합니다.");
        else categoryRepository.delete(category);
    }

}
