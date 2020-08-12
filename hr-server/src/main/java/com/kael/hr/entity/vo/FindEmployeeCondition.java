package com.kael.hr.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author kael
 * @Description  查询员工的条件类
 * @Date 2020/8/9 0009
 */
@ApiModel("查找员工的条件")
public class FindEmployeeCondition {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("政治面貌id")
    private Integer politicId;
    @ApiModelProperty("民族id")
    private Integer nationId;
    @ApiModelProperty("职位id")
    private Integer posId;
    @ApiModelProperty("职称id")
    private Integer jobLevelId;
    @ApiModelProperty("雇佣形式")
    private String engageForm;
    @ApiModelProperty("部门id")
    private Integer departmentId;
    @ApiModelProperty("入职时间范围")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date[] date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoliticId() {
        return politicId;
    }

    public void setPoliticId(Integer politicId) {
        this.politicId = politicId;
    }

    public Integer getNationId() {
        return nationId;
    }

    public void setNationId(Integer nationId) {
        this.nationId = nationId;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public Integer getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Integer jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public String getEngageForm() {
        return engageForm;
    }

    public void setEngageForm(String engageForm) {
        this.engageForm = engageForm;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Date[] getDate() {
        return date;
    }

    public void setDate(Date[] date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FindEmployeeCondition{" +
                "name='" + name + '\'' +
                ", politicId=" + politicId +
                ", nationId=" + nationId +
                ", posId=" + posId +
                ", jobLevelId=" + jobLevelId +
                ", engageForm='" + engageForm + '\'' +
                ", departmentId=" + departmentId +
                ", date=" + Arrays.toString(date) +
                '}';
    }
}
