package com.kael.hr.mapper;

import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    /**
     * 查找部门员工人数
     * @param deptId 部门id
     * @return 该部门员工人数
     */
    Long findEmpCountByDept(@Param("deptId") Integer deptId);
}
