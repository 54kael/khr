package com.kael.hr.mapper;

import com.kael.hr.entity.Employee;
import com.kael.hr.entity.vo.FindEmployeeCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    /**
     * 查找部门员工人数
     * @param deptId 部门id
     * @return 该部门员工人数
     */
    Long findEmpCountByDept(@Param("deptId") Integer deptId);

    /**
     * 按条件分页查询员工
     * @param limit 每页显示条数
     * @param offset 偏移量
     * @param cond 条件对象
     * @return 一定数量的符合条件的员工
     */
    List<Employee> findEmpByPageCondition(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("cond")FindEmployeeCondition cond);

    /**
     * 按条件查找员工数
     * @param cond 查询条件
     * @return 员工数
     */
    long getCountEmployeeByCondition(@Param("cond")FindEmployeeCondition cond);

    /**
     * 获取当天入职了多少员工
     * @param currentDay 当天时间字符串
     * @return 当天入职了多少员工
     */
    int getCountEmpByCurrentDay(@Param("currentDay") String currentDay);

    /**
     * 添加员工
     * @param emp 添加员工
     */
    void saveEmployee(@Param("emp") Employee emp);

    /**
     * 更新员工
     * @param employee 员工信息
     */
    void updateEmployee(@Param("emp") Employee emp);

    /**
     *
     */
    List<Employee> findExportDate();
}
