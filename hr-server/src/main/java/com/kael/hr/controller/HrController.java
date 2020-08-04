package com.kael.hr.controller;

import com.kael.hr.entity.Hr;
import com.kael.hr.entity.vo.HrLoginParameter;
import com.kael.hr.responst.Result;
import com.kael.hr.service.HrService;
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
@RequestMapping("/hr")
public class HrController {
    @Resource
    HrService hrService;

    @ApiOperation("hr登录")
    @PostMapping("/login")
    public Result login(@RequestBody @Validated HrLoginParameter hrLoginParameter) {
        String token = hrService.login(hrLoginParameter);
        return Result.ok().data("token",token);
    }

    @ApiOperation("根据username后取用户信息")
    @GetMapping("/info")
    public Result hrInfo(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        if (username==null) {
            return Result.failure("参数错误");
        }
        Hr hr = hrService.findHrInfoByUsername(username);
        return Result.ok().data("hrInfo",hr);
    }
}
