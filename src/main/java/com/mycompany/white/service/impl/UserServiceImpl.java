package com.mycompany.white.service.impl;

import com.mycompany.white.domain.entity.Role;
import com.mycompany.white.domain.entity.User;
import com.mycompany.white.domain.entity.UserRole;
import com.mycompany.white.repository.RoleRepository;
import com.mycompany.white.repository.UserRepository;
import com.mycompany.white.repository.UserRoleRepository;
import com.mycompany.white.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public void saveUser(String email, String password) {

        // 엔티티 조회
        Role role = roleRepository.findByRoleName("ROLE_USER");

        UserRole userRole = UserRole.builder()
                .role(role)
                .build();

        User user = User.builder()
                .email(email)
                .password(password)
                .userRole(userRole)
                .build();

        userRepository.save(user);
        userRoleRepository.save(userRole);
    }
}