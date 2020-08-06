package com.kael.hr.service;

import com.kael.hr.entity.Menu;

import java.util.List;

public interface MenuService {
    /**
     * 根据 username 查找菜单
     */
    List<Menu> findMenus(String username);
}
