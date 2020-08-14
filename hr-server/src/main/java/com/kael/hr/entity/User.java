package com.kael.hr.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class User {

    @ApiModelProperty(value = "id",hidden = true)
    private Integer id;

    @ApiModelProperty(value = "用户名",hidden = true)
    private String name;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty(value = "头像",hidden = true)
    private String userFace;

    @ApiModelProperty(value = "工号",hidden = true)
    private String workId;

    @ApiModelProperty(value = "用户角色",hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Role> roles;

    @ApiModelProperty(value = "用户状态",hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean enabled;
}
