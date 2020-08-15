package com.kael.hr.controller;

import com.kael.hr.entity.Account;
import com.kael.hr.responst.Result;
import com.kael.hr.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/4 0004
 */

@Slf4j
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody @Validated Account account) {
        String token = userService.login(account.getUsername(),account.getPassword());
        return Result.ok().data("token",token);
    }

    @ApiOperation("根据username后取用户信息")
    @GetMapping("/info")
    public Result userInfo(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        if (username==null) {
            log.error("请求头没有username");
            return Result.failure("尚未登录");
        }
        Account hr = userService.findAccountByUsername(username);
        return Result.ok().data("userInfo",hr);
    }
}
