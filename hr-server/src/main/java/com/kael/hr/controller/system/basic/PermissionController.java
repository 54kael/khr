package com.kael.hr.controller.system.basic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kael.hr.entity.Menu;
import com.kael.hr.entity.Role;
import com.kael.hr.responst.Result;
import com.kael.hr.service.MenuService;
import com.kael.hr.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/7 0007
 */
@Slf4j
@Api(tags = "权限管理")
@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {
    @Resource
    RoleService roleServiceImpl;

    @Resource
    MenuService menuServiceImpl;

    @ApiOperation("获取所有角色")
    @GetMapping
    public Result getAllRoles() {
        List<Role> allRoles = roleServiceImpl.findAllRoles();
        return Result.ok().data("allRoles",allRoles);
    }

    @ApiOperation("添加角色")
    @PostMapping("/role")
    public Result addRole(@RequestBody @Validated Role role) {
       roleServiceImpl.saveRole(role);
       return Result.ok();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/role/{rid}")
    public Result deleteRoleById(@PathVariable Integer rid) {
        roleServiceImpl.deleteRole(rid);
        return Result.ok();
    }

    @ApiOperation("获取所有菜单")
    @GetMapping("/menus")
    public Result getAllMenus() throws JsonProcessingException {
        List<Menu> menus = menuServiceImpl.findAllMenusIdAndName();
        return Result.ok().data("menus",menus);
    }

    @ApiOperation("获取角色的菜单id")
    @GetMapping("/menuIds/{rid}")
    public Result getMenuIdsByRid(@PathVariable("rid") Integer rid) {
        List<Integer> menuIds = menuServiceImpl.findMenuIdsByRoleId(rid);
        return Result.ok().data("mids",menuIds);
    }

    @ApiOperation("更新角色的菜单权限")
    @PutMapping
    public Result updateMenuRole(Integer rid, Integer[] mids) {
        menuServiceImpl.updateMenuByRoleId(rid,mids);
        return Result.ok();
    }

}
