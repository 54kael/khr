package com.kael.hr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kael.hr.entity.Department;
import com.kael.hr.entity.vo.DeptParam;

import java.util.List;

public interface DepartmentService {
    /**
     * 根据父级id获取所有子部门
     * @return 所有的部门
     */
    List<Department> findAllDepartmentsByParentId(Integer id) throws JsonProcessingException;

    /**
     * 添加部门
     * @param dept 需要添加的部门
     */
    void saveDepartment(DeptParam dept);

    /**
     * 根据部门id删除部门
     * @param deptId 部门id
     */
    void deletedDepartment(Integer deptId);

    /**
     * 更新部门
     * @param department 需要更新的部门
     */
    void updateDepartment(DeptParam department);
}
