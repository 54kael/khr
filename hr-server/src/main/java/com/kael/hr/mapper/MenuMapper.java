package com.kael.hr.mapper;

import com.kael.hr.entity.Menu;
import com.kael.hr.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    /**
     * 根据角色查找url
     * @param roles 角色列表
     * @return 包含 菜单id 和 菜单url 的菜单集合
     */
    List<Menu> findUrlsByRoles(@Param("roles") List<Role> roles);

    /**
     * 根据角色查找菜单
     * @param roles 角色
     * @return 菜单树
     */
    List<Menu> findMenusByRoles(@Param("roles") List<Role> roles);
}
