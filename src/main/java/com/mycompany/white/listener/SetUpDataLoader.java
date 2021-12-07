package com.mycompany.white.listener;

import com.mycompany.white.domain.entity.*;
import com.mycompany.white.repository.UserRepository;
import com.mycompany.white.repository.UserRoleRepository;
import com.mycompany.white.service.CategoryService;
import com.mycompany.white.service.PostService;
import com.mycompany.white.service.RoleService;
import com.mycompany.white.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {


    private final CategoryService categoryService;
    private final PostService postService;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Category category1 = Category.builder()
                .name("JAVA")
                .orderNum(1)
                .build();
        Category category2 = Category.builder()
                .name("공지사항")
                .orderNum(2)
                .build();
        Category category3 = Category.builder()
                .name("JPA")
                .orderNum(3)
                .build();

        categoryService.saveCategory(category1);
        categoryService.saveCategory(category2);
        categoryService.saveCategory(category3);

        for (int i = 0; i < 50; i++){
            Post post = Post.createPost("JAVA"+i, "내용입니다.", category1);

            postService.savePost(post.getCategory().getId(), post.getTitle(), post.getContent());
        }

        for (int i = 0; i < 55; i++){
            Post post = Post.createPost("공지사항"+i, "내용입니다.", category2);

            postService.savePost(post.getCategory().getId(), post.getTitle(), post.getContent());
        }
        for (int i = 0; i < 61; i++){
            Post post = Post.createPost("JPA"+i, "내용입니다.", category3);

            postService.savePost(post.getCategory().getId(), post.getTitle(), post.getContent());
        }

        Role role_user = Role.builder()
                .roleName("ROLE_USER")
                .roleDesc("사용자")
                .build();
        Role role_admin = Role.builder()
                .roleName("ROLE_ADMIN")
                .roleDesc("관리자")
                .build();

        roleService.saveRole(role_user);
        roleService.saveRole(role_admin);

        UserRole userRole = UserRole.builder()
                .role(role_admin)
                .build();
        User user = User.builder()
                .userRole(userRole)
                .email("seo970713@naver.com")
                .password(passwordEncoder.encode("qwe123"))
                .build();

        userRepository.save(user);
        userRoleRepository.save(userRole);
    }
}
