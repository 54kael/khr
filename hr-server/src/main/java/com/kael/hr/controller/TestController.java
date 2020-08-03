package com.kael.hr.controller;

import com.kael.hr.responst.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "测试接口")
@RequestMapping("/employee")
public class TestController {
    @GetMapping("/advanced/test1")
    @ApiOperation("测试1")
    public Result test1() {
        return Result.ok().data("result","yes");
    }

    @ApiOperation("测试2")
    @GetMapping("/test1")
    public Result test2() {
        return Result.ok();
    }
}
