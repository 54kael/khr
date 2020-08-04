package com.kael.hr.controller;

import com.kael.hr.entity.Menu;
import com.kael.hr.responst.Result;
import com.kael.hr.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/4 0004
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理")
public class MenuController {
    @Resource
    MenuService menuService;

    @ApiOperation("获取菜单")
    @GetMapping
    public Result getMenus(HttpServletRequest request) {
        String username = (String)request.getAttribute("username");
        List<Menu> menus = menuService.findMenus(username);
        return Result.ok().data("menus",menus);
    }
}
