package com.kael.hr.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author kael
 * @Description 民族实体类
 * @Date 2020/8/9 0009
 */
public class Nation {
    @ApiModelProperty("民族id")
    private Integer id;
    @ApiModelProperty("民族")
    private String name;

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
}
