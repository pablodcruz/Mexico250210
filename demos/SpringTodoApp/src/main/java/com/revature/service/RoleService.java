package com.revature.service;

import com.revature.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAllRoles();
    Optional<Role> findRoleById(Long id);
    Role createRole(Role role);
    Optional<Role> updateRole(Long id, Role role);
    boolean deleteRole(Long id);
}

