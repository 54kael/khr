package com.kael.hr.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kael.hr.entity.Department;
import com.kael.hr.entity.vo.DeptParam;
import com.kael.hr.exception.HrException;
import com.kael.hr.mapper.DepartmentMapper;
import com.kael.hr.mapper.EmployeeMapper;
import com.kael.hr.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/5 0005
 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    DepartmentMapper departmentMapper;

    @Resource
    EmployeeMapper employeeMapper;

    @Override
    public List<Department> findAllDepartmentsByParentId(Integer parentId) throws JsonProcessingException {
        List<Department> departments;
        ObjectMapper objectMapper = new ObjectMapper();
        if (parentId==-1) {
            String s = stringRedisTemplate.opsForValue().get("khr:allDepartment");
            if (s==null || "".equals(s)) {
                log.info("部门redis缓存为空");
                departments=departmentMapper.findAllDepartmentsByParentId(parentId);
                stringRedisTemplate.opsForValue().set("khr:allDepartment",objectMapper.writeValueAsString(departments));
            } else {
                log.info("部门redis缓存不为空");
                departments = objectMapper.readValue(s,List.class);
            }
            return departments;
        } else {
            return departmentMapper.findAllDepartmentsByParentId(parentId);
        }
    }

    @Override
    public void saveDepartment(DeptParam dept) {
        // 检查部门是否重复
        List<Department> departments = departmentMapper.findChildrenByParentId(dept.getParentId());
        for (Department department : departments) {
            if (department.getName().equals(dept.getName())) {
                log.error("部门名称重复");
                throw new HrException("部门名称重复");
            }
        }
        departmentMapper.saveDepartment(dept);
        stringRedisTemplate.delete("khr:allDepartment");
    }

    @Override
    public void deletedDepartment(Integer deptId) {
        // 检查该部门下面是否有子部门
        Integer childrenCount = departmentMapper.getChildrenCountByParent(deptId);
        if (childrenCount==0) {
            // 检查该部门是否还有员工
            Long empCount = employeeMapper.findEmpCountByDept(deptId);
            if (empCount==0) {
                log.info("删除部门={}=",deptId);
                departmentMapper.deleteDepartment(deptId);
                stringRedisTemplate.delete("khr:allDepartment");
            } else {
                log.error("={}=该部门还有员工",deptId);
                throw new HrException("该部门还有员工");
            }
        } else {
            log.error("={}=该部门还有子部门",deptId);
            throw new HrException("该部门还有子部门");
        }
    }

    @Override
    public void updateDepartment(DeptParam department) {
        departmentMapper.updateDepartment(department);
        stringRedisTemplate.delete("khr:allDepartment");
    }
}
