package com.mycompany.white.repository;

import com.mycompany.white.domain.dto.CategoryDto;
import com.mycompany.white.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


//    @Query("select new com.mycompany.white.domain.dto.CategoryDto (c.id, c.name, c.orderNum, count(p.id) as postNum) " +
//            "from Category c " +
//            "left outer join find_pill.post as p on c.id = p.category_id" +
//            "group by c.id order by c.orderNum asc")
//    List<CategoryDto> findAllCategoryJoinPost();

    @Query("select new com.mycompany.white.domain.dto.CategoryDto (c.id, c.name, c.orderNum, count(p.id) as postNum) " +
            "from Category c left outer join c.posts p " +
            "group by c.id order by c.orderNum asc")
    List<CategoryDto> findAllCategoryJoinPost();
}
