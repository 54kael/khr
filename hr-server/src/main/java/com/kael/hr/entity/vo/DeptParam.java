package com.kael.hr.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author kael
 * @Description 添加部门必须的参数
 * @Date 2020/8/5 0005
 */
public class DeptParam {
    @ApiModelProperty("部门id")
    private Integer id;

    @ApiModelProperty("部门名称")
    @NotNull(message = "部门名为空")
    private String name;

    @ApiModelProperty("上级部门id")
    @NotNull(message = "上级部门id为空")
    private Integer parentId;

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
}
