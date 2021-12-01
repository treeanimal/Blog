package com.mycompany.white.repository;

import com.mycompany.white.domain.dto.CategoryDto;
import com.mycompany.white.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


//    List<CategoryDto> findCategory
}
