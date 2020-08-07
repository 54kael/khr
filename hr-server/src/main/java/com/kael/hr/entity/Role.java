package com.kael.hr.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Role {

    @ApiModelProperty(value = "角色id",hidden = true)
    private Integer id;

    @NotNull(message = "角色名称为空")
    @ApiModelProperty("角色名称")
    private String name;

    @NotNull(message = "角色中文名为空")
    @ApiModelProperty("角色中文名")
    private String nameZh;

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
        this.name = name == null ? null : name.trim();
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameZh='" + nameZh + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return name.equals(role.name) ||
                nameZh.equals(role.nameZh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,nameZh);
    }
}
