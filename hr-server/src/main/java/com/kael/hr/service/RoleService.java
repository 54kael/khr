package com.kael.hr.service;

import com.kael.hr.entity.Role;

import java.util.List;

public interface RoleService {
    /**
     * 获取所有角色
     * @return 所有角色
     */
    List<Role> findAllRoles();

    /**
     * 添加角色
     * @param role 角色
     */
    void saveRole(Role role);

    /**
     * 删除角色
     * @param id 角色id
     */
    void deleteRole(Integer id);
}
