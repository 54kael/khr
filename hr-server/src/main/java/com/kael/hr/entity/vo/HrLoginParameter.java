package com.kael.hr.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author kael
 * @Description hr登录参数校验
 * @Date 2020/8/4 0004 9:35
 */
@ApiModel
public class HrLoginParameter {
    @ApiModelProperty("用户名")
    @NotNull(message = "用户名为空")
    private String username;

    @ApiModelProperty("用户密码")
    @NotNull(message = "密码为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
