package com.mycompany.white.repository;

import com.mycompany.white.domain.dto.CategoryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired private CategoryRepository categoryRepository;

    @Test
    public void 카테고리리스트와게시글갯수(){
        List<CategoryDto> findCategory = categoryRepository.findAllCategoryJoinPost();

        CategoryDto categoryDto = findCategory.get(0);

        Assertions.assertEquals(categoryDto.getName(), "JAVA");
    }

}