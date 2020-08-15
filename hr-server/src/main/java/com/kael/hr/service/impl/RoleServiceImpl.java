package com.kael.hr.service.impl;

import com.kael.hr.entity.Role;
import com.kael.hr.exception.HrException;
import com.kael.hr.mapper.MenuMapper;
import com.kael.hr.mapper.RoleMapper;
import com.kael.hr.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/7 0007
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;

    @Resource
    MenuMapper menuMapper;
    @Override
    public List<Role> findAllRoles() {
        return roleMapper.findAllRoles();
    }

    @Override
    public void saveRole(Role role) {
        List<Role> roleList = roleMapper.findAllRoles();
        if (roleList.size() != 0) {
            for (Role role1 : roleList) {
                if (role.equals(role1)) {
                    throw new HrException("该角色已存在！");
                }
            }
        }
        roleMapper.saveRole(role);
    }

    @Transactional
    @Override
    public void deleteRole(Integer id) {
        int hrCount = roleMapper.getCountAccountByRole(id);
        if (hrCount>0) {
            throw new HrException("该角色下还有关联账号");
        }
        roleMapper.deleteRole(id);
        menuMapper.deleteMenuByRoleId(id);
    }
}
