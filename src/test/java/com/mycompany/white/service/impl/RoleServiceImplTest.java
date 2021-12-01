package com.mycompany.white.service.impl;

import com.mycompany.white.domain.entity.Role;
import com.mycompany.white.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void 권한저장(){
        Role role = Role.builder()
                .roleName("ROLE_USER")
                .roleName("사용자")
                .build();

        roleService.saveRole(role);
    }

}