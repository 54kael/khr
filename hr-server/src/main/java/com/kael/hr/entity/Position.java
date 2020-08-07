package com.kael.hr.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author kael
 * @Description 职位实体类
 * @Date 2020/8/6 0006
 */
public class Position {

    @ApiModelProperty("职位id")
    private Integer id;

    @ApiModelProperty("职位名称")
    @NotNull(message = "职位名称为空")
    private String name;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createDate;

    @ApiModelProperty("是否启用")
    private Integer enabled;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}
