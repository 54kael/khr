package com.kael.hr.service.impl;

import com.kael.hr.entity.User;
import com.kael.hr.entity.Menu;
import com.kael.hr.entity.Role;
import com.kael.hr.exception.HrException;
import com.kael.hr.mapper.UserMapper;
import com.kael.hr.mapper.MenuMapper;
import com.kael.hr.mapper.RoleMapper;
import com.kael.hr.service.UserService;
import com.kael.hr.util.JwtUtil;
import com.kael.hr.util.PasswordEncrypted;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/4 0004 9:31
 */

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    MenuMapper menuMapper;
    @Resource
    RoleMapper roleMapper;

    @Override
    public String login(User user) {
        String loginPassword = user.getPassword();
        String username = user.getUsername();
        String password = userMapper.findPasswordByUsername(username);
        if (password==null) {
            throw new HrException("用户不存在");
        }
        if (!PasswordEncrypted.encrypted(loginPassword).equals(password)) {
            throw new HrException("用户名或者密码错误");
        }

        List<Role> roles = roleMapper.findRolesByUsername(username);
        List<Menu> menus = menuMapper.findUrlsByRoles(roles);
        return JwtUtil.createJwt(username, menus);
    }

    @Override
    public User findUserInfoByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }
}