package com.kael.hr.mapper;

import com.kael.hr.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/4 0004
 */
public interface RoleMapper {
    /**
     * 根据用户名查找角色
     */
    List<Role> findRolesByUsername(@Param("username") String username);

    /**
     * 获取所有角色
     * @return 所有角色
     */
    List<Role> findAllRoles();

    /**
     * 添加角色
     * @param role 角色
     */
    void saveRole(@Param("role") Role role);

    /**
     * 删除角色
     * @param id 角色id
     */
    void deleteRole(@Param("id") Integer id);

    /**
     * 查找该角色下 账号 人数
     * @param id 角色id
     * @return 使用该角色的账号
     */
    int getCountAccountByRole(@Param("id") Integer id);

    /**
     * 保存账号的角色
     * @param accountId 账号id
     * @param roleIds 账号角色列表
     */
    void saveAccountRole(@Param("accountId")Integer accountId, @Param("roleIds") List<Integer> roleIds);

}
