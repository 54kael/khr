package com.kael.hr.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author kael
 * @Description 政治面貌实体类
 * @Date 2020/8/9 0009
 */
public class Politics {
    @ApiModelProperty("政治面貌id")
    private Integer id;
    @ApiModelProperty("政治面貌")
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
