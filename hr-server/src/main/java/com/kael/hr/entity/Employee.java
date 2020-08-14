package com.kael.hr.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Author kael
 * @Description  添加员工或者更新员工的实体类
 * @Date 2020/8/6 0006
 */
@Getter
@Setter
@ToString
@ApiModel
public class Employee {
    @ColumnWidth(12)
    @ExcelProperty(value = "工号")
    @ApiModelProperty("工号")
    private String workId;

    @ColumnWidth(10)
    @ExcelProperty("姓名")
    @NotNull(message = "姓名为空")
    @ApiModelProperty("姓名")
    private String name;

    @ColumnWidth(10)
    @ExcelProperty("性别")
    @NotNull(message = "性别为空")
    @ApiModelProperty("性别")
    private String gender;

    @ColumnWidth(15)
    @DateTimeFormat("yyyy年MM月dd日")
    @ExcelProperty("出生日期")
    @NotNull(message = "出生日期为空")
    @ApiModelProperty("生日")
    private Date birthday;

    @ColumnWidth(20)
    @ExcelProperty("身份证")
    @NotNull(message = "身份证为空")
    @ApiModelProperty("身份证")
    private String idCard;

    @ColumnWidth(12)
    @ExcelProperty("婚姻状况")
    @NotNull(message = "婚姻状况为空")
    @ApiModelProperty("婚姻状况")
    private String wedlock;

    @ColumnWidth(15)
    @ExcelProperty("籍贯")
    @NotNull(message = "籍贯为空")
    @ApiModelProperty("籍贯")
    private String nativePlace;

    @ColumnWidth(25)
    @ExcelProperty("邮箱")
    @Email(message = "邮箱格式不对")
    @NotNull(message = "邮箱为空")
    @ApiModelProperty("邮箱")
    private String email;

    @ColumnWidth(15)
    @ExcelProperty("手机")
    @Pattern(regexp="^1([3-9])\\d{9}$",message="手机号格式不正确")
    @NotNull(message = "手机号码为空")
    @ApiModelProperty("手机号码")
    private String phone;

    @ColumnWidth(15)
    @ExcelProperty("住址")
    @NotNull(message = "家庭住址为空")
    @ApiModelProperty("家庭住址")
    private String address;

    @ExcelIgnore
    @NotNull(message = "部门为空")
    @ApiModelProperty("部门id")
    private Integer departmentId;

    @ExcelIgnore
    @NotNull(message = "职称为空")
    @ApiModelProperty("职称id")
    private Integer jobLevelId;

    @ExcelIgnore
    @NotNull(message = "岗位id为空")
    @ApiModelProperty("岗位id")
    private Integer posId;

    @ColumnWidth(12)
    @ExcelProperty("雇佣形式")
    @NotNull(message = "雇佣形式为空")
    @ApiModelProperty("雇佣形式")
    private String engageForm;

    @ColumnWidth(10)
    @ExcelProperty("学历")
    @NotNull(message = "学历为空")
    @ApiModelProperty("最高学历")
    private String tiptopDegree;

    @ColumnWidth(10)
    @ExcelProperty("专业")
    @NotNull(message = "专业为空")
    @ApiModelProperty("专业")
    private String specialty;

    @ColumnWidth(15)
    @ExcelProperty("学校")
    @NotNull(message = "学校为空")
    @ApiModelProperty("学校")
    private String school;

    @ColumnWidth(15)
    @ExcelProperty("入职时间")
    @DateTimeFormat("yyyy年MM月dd日")
    @NotNull(message = "入职时间为空")
    @ApiModelProperty("入职时间")
    private Date beginDate;

    @ColumnWidth(15)
    @ExcelProperty("转正日期")
    @DateTimeFormat("yyyy年MM月dd日")
    @ApiModelProperty(value = "转正日期",hidden = true)
    private Date conversionTime;

    @ColumnWidth(16)
    @ExcelProperty("合同起始日期")
    @DateTimeFormat("yyyy年MM月dd日")
    @NotNull(message = "合同起始日期为空")
    @ApiModelProperty("合同起始日期")
    private Date beginContract;

    @ColumnWidth(15)
    @ExcelProperty("合同期限")
    @ApiModelProperty(hidden = true)
    private Double contractTerm;

    @ColumnWidth(16)
    @ExcelProperty("合同结束日期")
    @DateTimeFormat("yyyy年MM月dd日")
    @NotNull(message = "合同结束日期为空")
    @ApiModelProperty("合同结束日期")
    private Date endContract;

    @ExcelIgnore
    @ApiModelProperty(hidden = true)
    private Integer id;

    @NotNull(message = "民族为空")
    @ApiModelProperty("民族")
    @ColumnWidth(12)
    @ExcelProperty("民族")
    private String nation;

    @NotNull(message = "政治面貌为空")
    @ColumnWidth(15)
    @ApiModelProperty("政治面貌")
    @ExcelProperty("政治面貌")
    private String politic;

    @ColumnWidth(12)
    @ApiModelProperty(hidden = true)
    @ExcelProperty("部门")
    private String department;

    @ColumnWidth(12)
    @ApiModelProperty(hidden = true)
    @ExcelProperty("职称")
    private String jobLevel;

    @ColumnWidth(12)
    @ApiModelProperty(hidden = true)
    @ExcelProperty("职位")
    private String pos;

    @ColumnWidth(12)
    @ApiModelProperty(hidden = true)
    @ExcelProperty("在职状态")
    private String workState;
}
