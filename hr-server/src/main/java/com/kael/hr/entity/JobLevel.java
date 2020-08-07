package com.kael.hr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * @Author kael
 * @Description 职称实体类
 * @Date 2020/8/6 0006
 */
public class JobLevel {

    @ApiModelProperty("职称id")
    private Integer id;

    @NotNull(message = "职称名称为空")
    @ApiModelProperty("职称名称")
    private String name;

    @NotNull(message = "级别为空")
    @ApiModelProperty("职称级别")
    private String titleLevel;

    // 只序列化成json,不从前端反序列化成对象
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createDate;

    @ApiModelProperty("是否开启")
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

    public String getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(String titleLevel) {
        this.titleLevel = titleLevel;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobLevel jobLevel = (JobLevel) o;
        return Objects.equals(name, jobLevel.name) &&
                Objects.equals(titleLevel, jobLevel.titleLevel) &&
                Objects.equals(enabled, jobLevel.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, titleLevel, enabled);
    }
}
