package com.mycompany.white.service;

import com.mycompany.white.domain.entity.Role;

public interface RoleService {

    void saveRole(Role role);

    Role findByRoleName(String roleName);
}
