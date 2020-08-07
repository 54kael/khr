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

    /**
     * 根据角色id查找菜单id
     * @param rid 角色id
     * @return 角色id下的菜单id列表
     */
    List<Integer> findMenuIdsByRoleId(@Param("rid") Integer rid);

    /**
     * 获取所有菜单的id和name
     * @return 所有菜单的id和name
     */
    List<Menu> findAllMenusIdAndName(@Param("id") Integer id);

    /**
     * 根据角色id删除菜单
     * @param rid 角色id
     */
    void deleteMenuByRoleId(@Param("rid") Integer rid);

    /**
     * 添加角色的所有菜单
     * @param rid 角色id
     * @param mIds 菜单的id
     */
    void saveMenu(@Param("rid")Integer rid, @Param("mIds")Integer[] mIds);
}
