package com.kael.hr.service.impl;

import com.kael.hr.entity.Menu;
import com.kael.hr.entity.Role;
import com.kael.hr.mapper.MenuMapper;
import com.kael.hr.mapper.RoleMapper;
import com.kael.hr.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/4 0004
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    RoleMapper roleMapper;
    @Resource
    MenuMapper menuMapper;
    @Override
    public List<Menu> findMenus(String username) {
        List<Role> roles = roleMapper.findRolesByUsername(username);
        return menuMapper.findMenusByRoles(roles);
    }
}
