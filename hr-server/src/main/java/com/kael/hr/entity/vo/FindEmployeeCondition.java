package com.kael.hr.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author kael
 * @Description  查询员工的条件类
 * @Date 2020/8/9 0009
 */
@Getter
@Setter
@ToString
@ApiModel("查找员工的条件")
public class FindEmployeeCondition {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("政治面貌")
    private String politic;
    @ApiModelProperty("民族")
    private String nation;
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
}
