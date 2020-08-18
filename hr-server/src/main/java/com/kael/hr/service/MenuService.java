package com.kael.hr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kael.hr.entity.Menu;

import java.util.List;

public interface MenuService {
    /**
     * 根据 username 查找菜单
     */
    List<Menu> findMenus(String username);

    /**
     * 查找所有菜单的id和name
     */
    List<Menu> findAllMenusIdAndName() throws JsonProcessingException;

    /**
     * 根据角色id获取所有菜单id
     * @return 角色的菜单id
     */
    List<Integer> findMenuIdsByRoleId(Integer rid);

    /**
     * 更新角色的菜单
     * @param rid 角色id
     * @param mids 新的菜单id
     */
    void updateMenuByRoleId(Integer rid, Integer[] mids);
}
