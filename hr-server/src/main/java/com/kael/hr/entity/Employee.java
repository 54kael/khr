package com.kael.hr.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/6 0006
 */
@Getter
@Setter
public class Employee {
    /**
     * id 主键
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 婚否
     */
    private String wedlock;

    /**
     * 民族
     */
    private Nation nation;

    /**
     * 祖籍省份
     */
    private String nativePlace;


    /**
     * 政治面貌
     */
    private Politics politics;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 住址
     */
    private String address;

    /**
     * 部门
     */
    private Department department;


    /**
     * 职称
     */
    private JobLevel jobLevel;


    /**
     * 岗位
     */
    private Position pos;

    /**
     * 雇佣形式
     */
    private String engageForm;

    /**
     * 最高学历
     */
    private String tiptopDegree;

    /**
     * 大学专业
     */
    private String specialty;

    /**
     * 毕业学校
     */
    private String school;

    /**
     * 入职时间
     */
    private Date beginDate;

    /**
     * 在职状态
     */
    private String workState;

    /**
     * 工号
     */
    private String workId;

    /**
     * 合同期限
     */
    private Double contractTerm;

    /**
     * 转正日期
     */
    private Date conversionTime;

    /**
     * 合同起始日期
     */
    private Date beginContract;

    /**
     * 合同结束日期
     */
    private Date endContract;

    /**
     * 工龄
     */
    private Integer workAge;
}
