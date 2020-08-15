package com.kael.hr.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Account {
    @NotNull(message = "账号为空")
    @ApiModelProperty("账号")
    private String username;

    @NotNull(message = "密码为空")
    @ApiModelProperty("密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty(value = "工号",hidden = true)
    private String workId;

    @ApiModelProperty(value = "账号id",hidden = true)
    private Integer id;

    @ApiModelProperty(value = "用户名",hidden = true)
    private String name;

    @ApiModelProperty(value = "账号状态",hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean enabled;

    @ApiModelProperty(value = "角色",hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Role> roles;

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

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
