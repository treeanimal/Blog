package com.mycompany.white.repository;

import com.mycompany.white.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.category order by p.id desc")
    List<Post> findAllPost();

    @Query("select p from Post p join fetch p.category")
    Optional<Post> findPostJoinCategory(Long postId);

    @Query("select p from Post p inner join p.category c where c.name = :name")
    Page<Post> findPostByCategoryName(@Param("name") String categoryName, Pageable pageable);

    @Query("select p from Post p join fetch p.category c where c.name = :name")
    List<Post> findAllPostByCategoryName(@Param("name") String categoryName);

    @Query("select p from Post p inner join p.category c where c.id = :id")
    Page<Post> findPostByCategoryId(@Param("id") Long categoryId, Pageable pageable);

}
