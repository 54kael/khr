package com.kael.hr.entity.vo;

import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/7 0007
 */
public class PermissionMenusVo {
    private Integer id;
    private String name;

    List<PermissionMenusVo> children;

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

    public List<PermissionMenusVo> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionMenusVo> children) {
        this.children = children;
    }
}
