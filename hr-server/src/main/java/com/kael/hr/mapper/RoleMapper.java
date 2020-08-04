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
}
