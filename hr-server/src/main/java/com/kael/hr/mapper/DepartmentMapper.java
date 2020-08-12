package com.kael.hr.mapper;

import com.kael.hr.entity.Department;
import com.kael.hr.entity.vo.DeptParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {

    /**
     * 根据父级id获取所有子部门
     * @return 该父级所有子部门
     */
    List<Department> findAllDepartmentsByParentId(@Param("parentId") int parentId);

    /**
     * 添加部门
     * @param dept 需要添加的部门
     */
    void saveDepartment(@Param("dept") DeptParam dept);

    /**
     * 获取部门下面有多少个子部门
     * @param id 部门id
     * @return 直接子部门个数
     */
    Integer getChildrenCountByParent(@Param("id") Integer id);

    /**
     * 根据部门id删除部门
     * @param id 部门id
     */
    void deleteDepartment(@Param("id") int id);

    /**
     * 根据父级id获取直接子部门
     * @param parentId 父级部门id
     * @return 该父级部门的直接子部门
     */
    List<Department> findChildrenByParentId(@Param("parentId") Integer parentId);

    /**
     * 更新部门
     * @param d 需要更新的部门
     */
    void updateDepartment(@Param("d") DeptParam d);

    /**
     * 根据部门id获取部门名称
     * @param id 部门id
     * @return 部门名称
     */
    String findDeptNameById(@Param("id") Integer id);
}
