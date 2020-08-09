package com.kael.hr.service;

import com.kael.hr.entity.vo.FindEmployeeCondition;
import com.kael.hr.entity.vo.PutEmpParam;

import java.util.Map;

public interface EmployeeService {

    int LIMIT=10;
    /**
     * 按条件分页查询员工
     * @param limit 每页显示条数
     * @param offset 偏移量
     * @param cond 条件对象
     * @return 一定数量的符合条件的员工
     */
    Map<String,Object> findEmpByPageCondition(int currentPage, FindEmployeeCondition cond);

    /**
     * 添加员工
     * @param employee 员工信息
     */
    void saveEmployee(PutEmpParam employee);

    /**
     * 更新员工
     * @param employee 员工信息
     */
    void updateEmployee(PutEmpParam employee);
}
