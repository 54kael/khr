package com.kael.hr.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author kael
 * @Description 部门实体类
 * @Date 2020/8/5 0005
 */
public class Department {
    /**
     * 部门id
     * */
    private Integer id;

    /**
     * 部门名
     */
    private String name;

    /**
     * 上级部门
     */
    private Integer parentId;

    /**
     * 部门id路径
     */
    private String depPath;

    /**
     * 是否启用 -1)启用；-0)关闭
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Byte enabled;

    /**
     * 是否父级部门 -1)是；0)否
     */
    private Byte isParent;

    /**
     * 子部门
     */
    List<Department> children;



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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDepPath() {
        return depPath;
    }

    public void setDepPath(String depPath) {
        this.depPath = depPath;
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    public Byte getIsParent() {
        return isParent;
    }

    public void setIsParent(Byte isParent) {
        this.isParent = isParent;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }
}
