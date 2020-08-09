package com.kael.hr.entity.vo;

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
 * @Description 添加或者更新员工的参数列表
 * @Date 2020/8/11 0011
 */
@Getter
@Setter
@ToString
public class PutEmpParam {

    @NotNull(message = "姓名为空")
    @ApiModelProperty("姓名")
    private String name;

    @NotNull(message = "性别为空")
    @ApiModelProperty("性别")
    private String gender;

    @NotNull(message = "出生日期为空")
    @ApiModelProperty("生日")
    private Date birthday;

    @NotNull(message = "身份证为空")
    @ApiModelProperty("身份证")
    private String idCard;

    @NotNull(message = "婚姻状况为空")
    @ApiModelProperty("婚姻状况")
    private String wedlock;

    @NotNull(message = "民族为空")
    @ApiModelProperty("民族id")
    private Integer nationId;

    @NotNull(message = "籍贯为空")
    @ApiModelProperty("籍贯")
    private String nativePlace;

    @NotNull(message = "政治面貌为空")
    @ApiModelProperty("政治面貌id")
    private Integer politicsId;

    @Email(message = "邮箱格式不对")
    @NotNull(message = "邮箱为空")
    @ApiModelProperty("邮箱")
    private String email;

    @Pattern(regexp="^1([3-9])\\d{9}$",message="手机号格式不正确")
    @NotNull(message = "手机号码为空")
    @ApiModelProperty("手机号码")
    private String phone;

    @NotNull(message = "家庭住址为空")
    @ApiModelProperty("家庭住址")
    private String address;

    @NotNull(message = "部门为空")
    @ApiModelProperty("部门id")
    private Integer departmentId;

    @NotNull(message = "职称为空")
    @ApiModelProperty("职称id")
    private Integer jobLevelId;

    @NotNull(message = "岗位id为空")
    @ApiModelProperty("岗位id")
    private Integer posId;

    @NotNull(message = "雇佣形式为空")
    @ApiModelProperty("雇佣形式")
    private String engageForm;

    @NotNull(message = "学历为空")
    @ApiModelProperty("最高学历")
    private String tiptopDegree;

    @NotNull(message = "专业为空")
    @ApiModelProperty("专业")
    private String specialty;

    @NotNull(message = "学校为空")
    @ApiModelProperty("学校")
    private String school;

    @NotNull(message = "入职时间为空")
    @ApiModelProperty("入职时间")
    private Date beginDate;

    @ApiModelProperty("工号")
    private String workId;

    @NotNull(message = "转正日期为空")
    @ApiModelProperty("转正日期")
    private Date conversionTime;

    @NotNull(message = "合同起始日期为空")
    @ApiModelProperty("合同起始日期")
    private Date beginContract;

    @NotNull(message = "合同结束日期为空")
    @ApiModelProperty("合同结束日期")
    private Date endContract;

    @ApiModelProperty("合同期限")
    private Double contractTerm;
}
