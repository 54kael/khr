package com.kael.hr.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kael.hr.entity.Menu;
import com.kael.hr.entity.Role;
import com.kael.hr.mapper.MenuMapper;
import com.kael.hr.mapper.RoleMapper;
import com.kael.hr.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/4 0004
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    RoleMapper roleMapper;
    @Resource
    MenuMapper menuMapper;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Override
    public List<Menu> findMenus(String username) {
        List<Role> roles = roleMapper.findRolesByUsername(username);
        return menuMapper.findMenusByRoles(roles);
    }

    @Override
    public List<Menu> findAllMenusIdAndName() throws JsonProcessingException {
        List<Menu> allMenuIdAndName;
        ObjectMapper objectMapper = new ObjectMapper();
        String s = stringRedisTemplate.opsForValue().get("khr:allMenuIdAndName");
        if (s==null || "".equals(s)) {
            log.info("所有菜单的id和名字缓存为空");
            allMenuIdAndName = menuMapper.findAllMenusIdAndName(0);
            stringRedisTemplate.opsForValue().set("khr:allMenuIdAndName",objectMapper.writeValueAsString(allMenuIdAndName));
            return allMenuIdAndName;
        } else {
            log.info("所有菜单的id和名字缓存不为空");
            return objectMapper.readValue(s,List.class);
        }
    }

    @Override
    public List<Integer> findMenuIdsByRoleId(Integer rid) {
        return menuMapper.findMenuIdsByRoleId(rid);
    }

    @Transactional
    @Override
    public void updateMenuByRoleId(Integer rid, Integer[] mids) {
        menuMapper.deleteMenuByRoleId(rid);
        menuMapper.saveMenu(rid,mids);
    }

}
